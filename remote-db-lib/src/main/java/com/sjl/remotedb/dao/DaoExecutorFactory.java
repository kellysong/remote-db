package com.sjl.remotedb.dao;

/**
 * TODO
 *
 * @author Kelly
 * @version 1.0.0
 * @filename DaoExecutorFactory.java
 * @time 2019/7/18 10:40
 * @copyright(C) 2019 song
 */
public class DaoExecutorFactory {
    /**
     * 创建SyncDaoExecutor
     *
     * @param dbName 数据源名字(db-config的dbName)
     * @return
     */
    public static SyncDaoExecutor createSyncDaoExecutor(String dbName) {
        return SyncDaoExecutor.init(dbName);
    }

    /**
     * 创建
     *
     * @param dbName 数据源名字(db-config的dbName)
     * @return
     */
    public static AsyncDaoExecutor createAsyncDaoExecutor(String dbName) {
        return AsyncDaoExecutor.init(dbName);
    }

}
