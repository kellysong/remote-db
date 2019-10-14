package com.sjl.remotedb.dao;

import com.sjl.remotedb.db.DbPoolManager;
import com.sjl.remotedb.db.SimpleDataSource;
import com.sjl.remotedb.page.Page;
import com.sjl.remotedb.page.SqlPageHandle;

import java.util.List;
import java.util.Map;

/**
 * 同步执行器SyncDaoExecutor,不同数据源new 一个
 * <p>
 * <p>1.由于是远程操作数据，涉及网络连接且数据可能存在延迟，必须在子线程操作</p>
 * <p>2.以下方法都是同步执行，可以结合其它异步框架进行异步操作，如RxJava</p>
 *
 * @author Kelly
 * @version 1.0.0
 * @filename SyncDaoExecutor.java
 * @time 2019/7/13 9:50
 * @copyright(C) 2019 song
 */
public class SyncDaoExecutor implements DaoExecutor {
    /**
     * 获取查询代理对象
     */
    private final IBaseDao baseDao;
    private final SimpleDataSource simpleDataSource;


    protected SyncDaoExecutor(String dbName) {
        this.baseDao = BaseDao.getProxyBaseDao(dbName);
        this.simpleDataSource = DbPoolManager.getInstance().getSingleDataSource(dbName);
        if (this.simpleDataSource == null) {
            throw new RuntimeException(dbName + " dataSource is uninitialized.");
        }
    }

    /**
     * 初始化dao执行器
     *
     * @param dbName 数据源名字
     * @return
     */
    public static SyncDaoExecutor init(String dbName) {
        return new SyncDaoExecutor(dbName);
    }


    @Override
    public <T> T queryBean(String sql, Class<T> classT, Object... params) {
        return (T) baseDao.queryBean(simpleDataSource.getConnection(), sql, classT, params);
    }


    @Override
    public <T> List<T> queryBeanList(String sql, Class<T> classT, Object... params) {
        return (List<T>) baseDao.queryBeanList(simpleDataSource.getConnection(), sql, classT, params);
    }

    @Override
    public long findTotalRecordNum(String sql, Object... params) {
        return baseDao.findTotalRecordNum(simpleDataSource.getConnection(), sql, params);
    }

    @Override
    public <T> Page<T> queryPagination(SqlPageHandle sqlPageHandle, Class<T> classT, Object[] params) {
        return baseDao.queryPagination(simpleDataSource.getConnection(), sqlPageHandle, classT, params);
    }


    @Override
    public Map<String, Object> queryBeanForMap(String sql, Object... params) {
        return baseDao.queryBeanForMap(simpleDataSource.getConnection(), sql, params);
    }

    @Override
    public Object queryBeanColumn(String sql, String columnName, Object... params) {
        return baseDao.queryBeanColumn(simpleDataSource.getConnection(), sql, columnName, params);
    }

    @Override
    public List<Map<String, Object>> queryBeanForListMap(String sql, Object... params) {
        return baseDao.queryBeanForListMap(simpleDataSource.getConnection(), sql, params);
    }

    @Override
    public boolean update(String sql, Object... params) {
        return baseDao.update(simpleDataSource.getConnection(), sql, params);
    }

    @Override
    public boolean updateInTx(String sql, Object... params) {
        return baseDao.updateInTx(simpleDataSource.getConnection(), sql, params);

    }

    @Override
    public boolean batchUpdateInTx(String sql, Object[][] params) {
        return baseDao.batchUpdateInTx(simpleDataSource.getConnection(), sql, params);
    }

    @Override
    public void batchDeleteInTx(String sql, String[] ids) {
        baseDao.batchDeleteInTx(simpleDataSource.getConnection(), sql, ids);
    }
}
