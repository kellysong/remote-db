package com.sjl.remotedb.dao;

import android.util.Log;

import com.sjl.remotedb.db.DbPoolManager;
import com.sjl.remotedb.db.SimpleDataSource;
import com.sjl.remotedb.page.Page;
import com.sjl.remotedb.page.SqlPageHandle;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * baseDao实现类
 * <p>1.DBUtils在创建QueryRunner时传入dataSource对象每次在执行完之后都会自动关闭Connection连接对象,
 * 但是由于自己自定义了连接池{@link SimpleDataSource}来获取连接，若操作自动关闭达不到复用的效果，
 * 故本次BaseDaoImpl不采用自动关闭连接，由连接池控制关闭;
 * 复用连接由BaseDaoImpl操作完后把连接放回连接池
 * </p>
 * <p>2.查询自动映射必须具备以下任一条件：
 * a.domain类的属性必须和数据库中名称一致，如createTime,createTime
 * b.domain类的属性和表中字段下划线分割一致,如createTime，create_time</p>
 *
 * @author Kelly
 * @version 1.0.0
 * @filename DbConfig.java
 * @time 2019/7/12 15:20
 * @copyright(C) 2019 song
 */
public class BaseDao<T> implements IBaseDao<T> {
    private static final String TAG = "BaseDao";

    private final QueryRunner queryRunner;
    private final String dbName;

    private BaseDao(String dbName) {
        this.queryRunner = new QueryRunner();
        this.dbName = dbName;
    }

    /**
     * 获取代理对象
     *
     * @return
     */
    public static IBaseDao getProxyBaseDao(String dbName) {
        final BaseDao target = new BaseDao(dbName);
        IBaseDao baseDao = (IBaseDao) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * proxy:代理类代理的真实代理对象com.sun.proxy.$Proxy0
                     * method:我们所要调用某个对象真实的方法的Method对象
                     * args:我们所要调用某个对象的方法参数
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Log.i(TAG, "当前执行方法：" + method.getName());
                        Object result = method.invoke(target, args);//调用目标对象的方法
                        Connection conn = (Connection) args[0];
                        target.returnConnection(conn);
                        return result;
                    }
                });
        return baseDao;
    }


    @Override
    public T queryBean(Connection conn, String sql, Class<T> classT, Object... params) {
        T result = null;
        try {
            result = queryRunner.query(conn, sql, new BeanHandler<T>(classT, getRowProcessor()), params);
            return result;
        } catch (Exception e) {
            Log.e(TAG, "queryBean 异常：" + sql, e);
            throw new DaoException("queryBean 异常：" + sql, e);
        }
    }


    @Override
    public List<T> queryBeanList(Connection conn, String sql, Class<T> classT, Object... params) {// 测试没问题
        List<T> result;
        try {
            result = queryRunner.query(conn, sql, new BeanListHandler<T>(classT, getRowProcessor()), params);
            return result;
        } catch (Exception e) {
            Log.e(TAG, "queryBeanList 异常：" + sql, e);
            throw new DaoException("queryBeanList 异常：" + sql, e);
        }
    }


    @Override
    public long findTotalRecordNum(Connection conn, String sql, Object... params) {// 测试没问题
        try {
            long totalRecordNum = (Long) queryRunner.query(conn, sql, new ScalarHandler(1), params);
            return totalRecordNum;
        } catch (Exception e) {
            Log.e(TAG, "findTotalRecordNum 异常：" + sql, e);
            throw new DaoException("findTotalRecordNum 异常：" + sql, e);
        }
    }

    @Override
    public <T> Page<T> queryPagination(Connection conn, SqlPageHandle sqlPageHandle, Class<T> classT, Object[] params) {
        try {
            List<T> list;
            List<T> totalList;
            // 将SQL语句进行分页处理
            String sql = sqlPageHandle.oldSQL;
            String newSql = sqlPageHandle.handlerPagingSQL();
            if (params == null || params.length <= 0) {
                totalList = queryRunner.query(conn, sql, new BeanListHandler<T>(classT, getRowProcessor()));
                list = queryRunner.query(conn, newSql, new BeanListHandler<T>(classT, getRowProcessor()));
            } else {
                totalList = queryRunner.query(conn, sql, new BeanListHandler<T>(classT, getRowProcessor()), params);
                list = queryRunner.query(conn, newSql, new BeanListHandler<T>(classT, getRowProcessor()), params);
            }
            Page<T> page = new Page<T>(sqlPageHandle.pageNo, sqlPageHandle.pageSize, totalList == null || totalList.isEmpty() ? 0 : totalList.size(), list);
            return page;
        } catch (Exception e) {
            Log.e(TAG, "queryPagination 异常", e);
            throw new DaoException("queryPagination 异常", e);
        }
    }


    @Override
    public Map<String, Object> queryBeanForMap(Connection conn, String sql, Object... params) {
        Map<String, Object> result;
        try {
            result = queryRunner.query(conn, sql, new MapHandler(getRowProcessor()), params);
            return result;
        } catch (Exception e) {
            Log.e(TAG, "queryBeanForMap 异常：" + sql, e);
            throw new DaoException("queryBeanForMap 异常：" + sql, e);
        }
    }

    @Override
    public Object queryBeanColumn(Connection conn, String sql, String columnName, Object... params) {
        Object result;
        try {
            result = queryRunner.query(conn, sql, new ScalarHandler(columnName), params);
            return result;
        } catch (Exception e) {
            Log.e(TAG, "queryBeanColumn 异常：" + sql, e);
            throw new DaoException("queryBeanColumn 异常：" + sql, e);
        }
    }

    @Override
    public List<Map<String, Object>> queryBeanForListMap(Connection conn, String sql, Object... params) {
        List<Map<String, Object>> result;
        try {
            result = queryRunner.query(conn, sql, new MapListHandler(getRowProcessor()), params);
            return result;
        } catch (Exception e) {
            Log.e(TAG, "queryBeanForListMap 异常：" + sql, e);
            throw new DaoException("queryBeanForListMap 异常：" + sql, e);
        }
    }


    @Override
    public boolean update(Connection conn, String sql, Object... params) { // 测试没问题
        try {
            int result = queryRunner.update(conn, sql, params);
            if (result >= 0) {//等于0说明没有该数据，大于0删除成功，小于0删除失败
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "update 异常：" + sql, e);
            throw new DaoException("update 异常：" + sql, e);
        }
    }

    @Override
    public boolean updateInTx(Connection conn, String sql, Object... params) {
        try {
            conn.setAutoCommit(false);// 开启事务
            int result = queryRunner.update(conn, sql, params);
            // 没有出错提交事务
            DbUtils.commit(conn);
            if (result >= 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            Log.e(TAG, "updateInTx 异常：" + sql, e);
            // 回滚事务
            try {
                DbUtils.rollback(conn);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DaoException("updateInTx 异常：" + sql, e);
        }
    }

    @Override
    public boolean batchUpdateInTx(Connection conn, String sql, Object[][] params) {
        try {
            conn.setAutoCommit(false);// 开启事务
            int[] batch = queryRunner.batch(conn, sql, params);
            Log.w(TAG, "batchUpdateInTx:" + Arrays.toString(batch));
            // 没有出错提交事务
            DbUtils.commit(conn);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "batchUpdateInTx 异常：" + sql, e);
            // 回滚事务
            try {
                DbUtils.rollback(conn);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DaoException("batchUpdateInTx 异常：" + sql, e);
        }
    }


    @Override
    public void batchDeleteInTx(Connection conn, String sql, String[] ids) {
        try {
            conn.setAutoCommit(false);// 开启事务
            QueryRunner queryRunner = new QueryRunner();
            for (String id : ids) {
                queryRunner.update(conn, sql, id);
            }
            // 没有出错提交事务
            DbUtils.commit(conn);
        } catch (Exception e) {
            Log.e(TAG, "batchDeleteInTx 异常：" + sql, e);
            // 回滚事务
            try {
                DbUtils.rollback(conn);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DaoException("batchDeleteInTx 异常：" + sql, e);
        }

    }


    /**
     * 归还连接
     *
     * @param connection
     */
    private void returnConnection(Connection connection) {
        DbPoolManager.getInstance().closeConnection(dbName, connection);
    }


    /**
     * 数据库表下划线转bean中驼峰格式
     *
     * @return
     */
    private RowProcessor getRowProcessor() {
        //开启下划线->驼峰转换所用
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        return processor;
    }

}
