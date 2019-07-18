package com.sjl.remotedb.db;

import android.content.Context;
import android.util.Log;

import com.sjl.remotedb.dao.DaoExecutorAnnotationProcessor;
import com.sjl.remotedb.util.AsyncTaskUtils;
import com.sjl.remotedb.util.Callback;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库连接池管理，管理多个数据源
 *
 * @author Kelly
 * @version 1.0.0
 * @filename DbPoolManager.java
 * @time 2019/7/14 13:52
 * @copyright(C) 2019 song
 */
public class DbPoolManager {
    private static final String TAG = "DbPoolManager";

    /**
     * 缓存连接池
     */
    private static final Map<String, SimpleDataSource> pools = new ConcurrentHashMap<>();
    /**
     * 初始化标志
     */
    private volatile boolean initFlag = false;

    private DbPoolManager() {

    }


    public static DbPoolManager getInstance() {
        return Singleton.instance;
    }

    private static class Singleton {
        private static DbPoolManager instance = new DbPoolManager();
    }


    /**
     * 根据配置文件初始化连接池
     * <p>assets目录下必须有db-config.xml文件</p>
     *
     * @param context
     * @param dbCallback
     */
    public void initDataSource(final Context context, final DbCallback dbCallback) {
        if (initFlag) {
            Log.w(TAG, "正在初始化连接池,请稍等...");
            return;
        }
        AsyncTaskUtils.doAsync(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                initFlag = true;
                List<DbConfig> dbConfigs = DbXmlParser.parseAsList(context);
                cachePool(dbConfigs);
                return true;
            }
        }, new Callback<Boolean>() {
            @Override
            public void accept(Boolean result) {
                if (dbCallback != null) {
                    dbCallback.onSuccess();
                }
                initFlag = false;
            }
        }, new Callback<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                Log.e(TAG, "初始化连接池异常", throwable);
                if (dbCallback != null) {
                    dbCallback.onFailed(throwable);
                }
                initFlag = false;
            }
        });
    }

    /**
     * 从远程加载db-config配置文件初始化连接池（安全系数高）
     *
     * @param url
     * @param dbCallback
     */
    public void initDataSource(final String url, final DbCallback dbCallback) {
        if (initFlag) {
            Log.w(TAG, "正在初始化连接池,请稍等...");
            return;
        }
        AsyncTaskUtils.doAsync(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                initFlag = true;
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/octet-stream");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.connect();
                InputStream inputStream = conn.getInputStream();
                List<DbConfig> dbConfigs = DbXmlParser.parseAsList(inputStream);
                cachePool(dbConfigs);
                return true;
            }
        }, new Callback<Boolean>() {
            @Override
            public void accept(Boolean result) {
                if (dbCallback != null) {
                    dbCallback.onSuccess();
                }
                initFlag = false;
            }
        }, new Callback<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                Log.e(TAG, "初始化连接池异常", throwable);
                if (dbCallback != null) {
                    dbCallback.onFailed(throwable);
                }
                initFlag = false;
            }
        });
    }


    /**
     * 根据dbConfig初始化连接池
     *
     * @param dbConfig
     * @param dbCallback
     */
    public void initDataSource(final DbConfig dbConfig, final DbCallback dbCallback) {
        List<DbConfig> dbConfigs = new ArrayList<DbConfig>();
        dbConfigs.add(dbConfig);
        initDataSource(dbConfigs, dbCallback);
    }


    /**
     * 根据dbConfigs初始化连接池
     *
     * @param dbConfigs
     * @param dbCallback
     */
    public void initDataSource(final List<DbConfig> dbConfigs, final DbCallback dbCallback) {
        if (initFlag) {
            Log.w(TAG, "正在初始化连接池,请稍等...");
            return;
        }
        AsyncTaskUtils.doAsync(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                initFlag = true;
                cachePool(dbConfigs);
                return true;
            }
        }, new Callback<Boolean>() {
            @Override
            public void accept(Boolean result) {
                if (dbCallback != null) {
                    dbCallback.onSuccess();
                }
                initFlag = false;
            }
        }, new Callback<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                Log.e(TAG, "初始化连接池异常", throwable);
                if (dbCallback != null) {
                    dbCallback.onFailed(throwable);
                }
                initFlag = false;
            }
        });

    }


    /**
     * 缓存连接池
     *
     * @param dbConfigs
     */
    private void cachePool(List<DbConfig> dbConfigs) throws Exception {
        synchronized (this) {
            for (int i = 0; i < dbConfigs.size(); i++) {
                DbConfig dbConfig = dbConfigs.get(i);
                if (pools.containsKey(dbConfig.getDbName())) {//先把之前的关闭，再缓存
                    closeDataSource(dbConfig.getDbName());
                    pools.remove(dbConfig.getDbName());
                }
                if (dbConfig.isActive()) {
                    SimpleDataSource simpleDataSource = new SimpleDataSource();
                    simpleDataSource.initDataSource(dbConfig);
                    pools.put(dbConfig.getDbName(), simpleDataSource);
                    Log.i(TAG, "init connection success:" + dbConfig.getDbName());
                }
            }
        }
    }

    /**
     * 根据连接池名字获取单个连接池
     *
     * @param dbName
     * @return
     */
    public SimpleDataSource getSingleDataSource(String dbName) {
        SimpleDataSource pool = null;
        if (pools.size() > 0 && pools.containsKey(dbName)) {
            pool = pools.get(dbName);
        }
        return pool;
    }

    /**
     * 根据连接池名字获取连接
     *
     * @param dbName
     * @return
     */
    public Connection getConnection(String dbName) {
        Connection conn = null;
        if (pools.size() > 0 && pools.containsKey(dbName)) {
            conn = getSingleDataSource(dbName).getConnection();
        } else {
            Log.e(TAG, "can't find this connection pool:" + dbName);
        }
        return conn;
    }

    /**
     * 回收连接
     *
     * @param dbName
     * @param conn
     */
    public void closeConnection(String dbName, Connection conn) {
        SimpleDataSource pool = getSingleDataSource(dbName);
        if (pool != null) {
            pool.closeConnection(conn);
        }
    }

    /**
     * 关闭单个连接池
     *
     * @param dbName
     */
    public void closeDataSource(String dbName) {
        SimpleDataSource pool = getSingleDataSource(dbName);
        if (pool != null) {
            pool.shutDataSource();
            Log.i(TAG, "close pool success:" + dbName);
        }
    }


    /**
     * 销毁所有连接池
     */
    public void destroy() {
        DaoExecutorAnnotationProcessor.clear();
        initFlag = false;
        if (pools.size() > 0) {
            for (Map.Entry<String, SimpleDataSource> entry : pools.entrySet()) {
                String dbName = entry.getKey();
                closeDataSource(dbName);
            }
        }
    }


}
