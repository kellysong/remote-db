package com.sjl.remotedb.db;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一个简单的连接池功能
 * <p>数据库连接池负责分配,管理和释放数据库连接,它允许应用程序重复使用一个现有的数据库连接,而不是重新建立一个</p>
 *
 * @author Kelly
 * @version 1.0.0
 * @filename SimpleDataSource.java
 * @time 2019/7/11 17:44
 * @copyright(C) 2019 song
 */
public class SimpleDataSource extends AbstractDataSource {
    private static final String TAG = "SimpleDataSource";

    /**
     * 连接配置类
     */
    private DbConfig dbConfig;


    /**
     * 空闲连接池
     */
    private final LinkedList<Connection> freeConnection = new LinkedList<>();

    /**
     * 固定连接池
     */
    private final LinkedList<Connection> fixConnection = new LinkedList<>();
    /**
     * 当前活动连接数量
     */
    private final AtomicInteger activeNum = new AtomicInteger(0);


    /**
     * 超时机制
     */
    private final Map<Integer, Long> timeOutMap = new ConcurrentHashMap<>();

    /**
     * 监听线程
     */
    private MonitorThread monitorThread;


    /**
     * 数据源是否关闭
     */
    private volatile boolean isShut = false;

    /**
     * 初始化连接放入连接池
     * （建议放在子线程）
     */
    public void initDataSource(DbConfig dbConfig) throws Exception {
        this.dbConfig = dbConfig;
        Log.i(TAG, "开始初始化连接池:\n" + dbConfig);
        //初始化连接并把连接放入连接池
        long start = System.currentTimeMillis();
        for (int i = 0; i < dbConfig.getInitialPoolSize(); i++) {
            fixConnection.addLast(createConnection());
            Log.i(TAG, "开始初始化连接池:" + (i + 1));

        }
        long end = System.currentTimeMillis();
        Log.i(TAG, "初始化连接池成，大小：" + fixConnection.size() + ",耗时:" + (end - start) / 1000 + "s");
        timeoutMonitor();

    }

    /**
     * 创建连接
     *
     * @return
     */
    private Connection createConnection() throws Exception {
        Class.forName(dbConfig.getDriverClass());
        final Connection conn = DriverManager.getConnection(dbConfig.getJdbcUrl(), dbConfig.getUsername(), dbConfig.getPassword());
        return conn;
    }

    @Override
    public synchronized Connection getConnection() {
        if (isShut) {
            throw new RuntimeException("连接池已经关闭，获取连接失败");
        }
        Log.i(TAG, "=======正在获取连接start ==========");
        Log.i(TAG, "当前空闲连接数:" + freeConnection.size() + ",固定连接数:" + fixConnection.size() + ",当前活动连接数:" + activeNum.get());
        Connection conn = null;
        if (freeConnection.size() > 0) {
            conn = freeConnection.removeFirst();
            addActiveNum();
        } else {
            if (fixConnection.size() > 0) {
                conn = fixConnection.removeFirst();
                addActiveNum();
            } else {
                int max = activeNum.get() + fixConnection.size() + freeConnection.size();
                if (max < dbConfig.getMaxPoolSize()) {
                    try {
                        conn = createConnection();
                        addActiveNum();
                    } catch (Exception e) {
                        Log.e(TAG, "创建连接异常", e);
                    }
                } else {
                    //超出连接数,等待
                    try {
                        wait(10 * 1000);
                        conn = getConnection();
                    } catch (InterruptedException e) {
                        Log.e(TAG, "等待连接异常", e);
                    }
                }
            }
        }
        Log.i(TAG, "=======获取连接end ==========");
        Log.i(TAG, "当前空闲连接数:" + freeConnection.size() + ",固定连接数:" + fixConnection.size() + ",当前活动连接数:" + activeNum.get());
        return conn;
    }

    /**
     * 释放链接
     *
     * @param conn
     */
    public synchronized void closeConnection(Connection conn) {
        if (conn == null) {
            return;
        }
        Log.w(TAG, "=======正在释放连接 start==========");
        Log.w(TAG, "当前空闲连接数:" + freeConnection.size() + ",固定连接数:" + fixConnection.size() + ",当前活动连接数:" + activeNum.get());
        if (fixConnection.size() < dbConfig.getInitialPoolSize()) {
            fixConnection.add(conn);
        } else {
            freeConnection.add(conn);
            timeOutMap.put(conn.hashCode(), System.currentTimeMillis());
        }
        removeActiveNum();
        Log.w(TAG, "=======释放连接 end==========");
        Log.w(TAG, "当前空闲连接数:" + freeConnection.size() + ",固定连接数:" + fixConnection.size() + ",当前活动连接数:" + activeNum.get());
        // 唤醒所有正等待的线程
        notifyAll();
    }


    /**
     * 判断连接是否可用
     *
     * @param conn
     * @return
     */
    public boolean isValid(Connection conn) {
        try {
            if (conn == null || conn.isClosed()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void addActiveNum() {
        activeNum.incrementAndGet();
    }

    private void removeActiveNum() {
        activeNum.decrementAndGet();

    }


    /**
     * 空闲连接超时检测
     */
    private void timeoutMonitor() {
        monitorThread = new MonitorThread(this);
        monitorThread.start();
    }


    private static final class MonitorThread extends Thread {
        private WeakReference<SimpleDataSource> simpleDataSourceReference;

        /**
         * 线程运行状态
         */
        private volatile boolean isRunning = true;

        public MonitorThread(SimpleDataSource simpleDataSource) {
            simpleDataSourceReference = new WeakReference<>(simpleDataSource);
        }

        @Override
        public void run() {
            SimpleDataSource simpleDataSource = simpleDataSourceReference.get();
            if (simpleDataSource == null) {
                return;
            }
            while (isRunning) {
                LinkedList<Connection> fixConnection = simpleDataSource.fixConnection;
                LinkedList<Connection> freeConnection = simpleDataSource.freeConnection;
                Map<Integer, Long> timeOutMap = simpleDataSource.timeOutMap;
                try {
                    for (int i = freeConnection.size() - 1; i >= 0; i--) {
                        Connection connection = freeConnection.get(i);
                        int hc = connection.hashCode();
                        Long time = timeOutMap.get(hc);
                        if (time != null
                                && (System.currentTimeMillis() - time) >= simpleDataSource.dbConfig.getKeepAliveTime()
                                && freeConnection.contains(connection)) {
                            freeConnection.remove(i).close();
                            timeOutMap.remove(hc);
                            Log.w(TAG, "检测到有空闲连接超时，被释放掉");
                            Log.w(TAG, "当前空闲连接数:" + freeConnection.size() + ",固定连接数:" + fixConnection.size() + ",当前活动连接数:" + simpleDataSource.activeNum.get());
                        }
                    }
                } catch (Exception e) {
                    Log.e(TAG, "释放掉异常", e);
                }
                if (!isRunning) {
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 判断连接是否可用
     *
     * @param connection
     * @return
     */
    private boolean isAvailable(Connection connection) {
        try {
            if (connection == null || connection.isClosed()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 关闭数据源
     */
    public void shutDataSource() {
        if (monitorThread != null) {
            monitorThread.isRunning = false;
            monitorThread = null;
        }
        Log.w(TAG, "开始关闭数据源：" + dbConfig.getDbName());
        Log.w(TAG, "   停止线程检测成功");
        for (int i = 0; i < freeConnection.size(); i++) {
            try {
                freeConnection.get(i).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        freeConnection.clear();
        Log.w(TAG, "   关闭空闲连接成功");
        for (int i = 0; i < fixConnection.size(); i++) {
            try {
                fixConnection.get(i).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        fixConnection.clear();
        Log.w(TAG, "   关闭固定连接成功");
        timeOutMap.clear();
        isShut = true;
        Log.w(TAG, "   连接池关闭完毕");
        Log.w(TAG, "关闭数据源结束：" + dbConfig.getDbName());

    }

    /**
     * 判断是否关闭
     *
     * @return
     */
    public boolean isShut() {
        return isShut;
    }


}
