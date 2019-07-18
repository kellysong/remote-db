package com.sjl.remotedb;

import android.content.Context;

import com.sjl.remotedb.dao.AsyncDaoExecutor;
import com.sjl.remotedb.dao.DaoExecutorAnnotationProcessor;
import com.sjl.remotedb.dao.DaoExecutorFactory;
import com.sjl.remotedb.dao.SyncDaoExecutor;
import com.sjl.remotedb.db.DbCallback;
import com.sjl.remotedb.db.DbConfig;
import com.sjl.remotedb.db.DbPoolManager;

import java.util.List;

/**
 * TODO
 *
 * @author Kelly
 * @version 1.0.0
 * @filename RemoteDb.java
 * @time 2019/7/17 11:07
 * @copyright(C) 2019 song
 */
public class RemoteDb {
    private static volatile RemoteDb remoteDb;
    private final DbPoolManager dbPoolManager;

    private RemoteDb() {
        this.dbPoolManager = DbPoolManager.getInstance();
    }

    public static RemoteDb get() {
        return create();
    }

    private static RemoteDb create() {
        if (remoteDb == null) {
            synchronized (RemoteDb.class) {
                if (remoteDb == null) {
                    remoteDb = new RemoteDb();
                }
            }
        }
        return remoteDb;
    }

    /**
     * 初始化数据源
     *
     * @param context
     * @param dbCallback
     */
    public void initDataSource(Context context, DbCallback dbCallback) {
        dbPoolManager.initDataSource(context, dbCallback);
    }

    /**
     * 初始化数据源
     *
     * @param url
     * @param dbCallback
     */
    public void initDataSource(String url, DbCallback dbCallback) {
        dbPoolManager.initDataSource(url, dbCallback);
    }

    /**
     * 初始化数据源
     *
     * @param dbConfig
     * @param dbCallback
     */
    public void initDataSource(DbConfig dbConfig, DbCallback dbCallback) {
        dbPoolManager.initDataSource(dbConfig, dbCallback);
    }

    /**
     * 初始化数据源
     *
     * @param dbConfigs
     * @param dbCallback
     */
    public void initDataSource(List<DbConfig> dbConfigs, DbCallback dbCallback) {
        dbPoolManager.initDataSource(dbConfigs, dbCallback);
    }

    /**
     * 注入对象
     *
     * @param objects
     * @throws Exception
     */
    public void inject(Object... objects) throws Exception {
        DaoExecutorAnnotationProcessor.processAnnotations(objects);
    }

    /**
     * 获取SyncDaoExecutor
     * @param dbName
     * @return
     */
    public SyncDaoExecutor getSyncDaoExecutor(String dbName) {
        return DaoExecutorFactory.createSyncDaoExecutor(dbName);
    }

    /**
     * 获取AsyncDaoExecutor
     * @param dbName
     * @return
     */
    public AsyncDaoExecutor getAsyncDaoExecutor(String dbName) {
        return DaoExecutorFactory.createAsyncDaoExecutor(dbName);
    }


    /**
     * 关闭
     */
    public void close() {
        DaoExecutorAnnotationProcessor.clear();
        dbPoolManager.destroy();
    }


}