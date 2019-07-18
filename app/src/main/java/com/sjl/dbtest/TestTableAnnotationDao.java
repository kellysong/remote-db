package com.sjl.dbtest;

import com.sjl.remotedb.annotation.InjectDao;
import com.sjl.remotedb.dao.AsyncDaoExecutor;
import com.sjl.remotedb.dao.SyncDaoExecutor;

import java.util.List;

/**
 * TODO
 *
 * @author Kelly
 * @version 1.0.0
 * @filename TestTableDao.java
 * @time 2019/7/12 15:52
 * @copyright(C) 2019 song
 */
public class TestTableAnnotationDao {

    /**
     * 注入同步SyncDaoExecutor,isAsync必须是false，否则注入失败
     */
    @InjectDao(dbName = AppConstant.DB_MYSQL, isAsync = false)
    SyncDaoExecutor syncDaoExecutor;


    public List<TestTable> query(int num) {
        return syncDaoExecutor.queryBeanList("select * from test_table  limit  0," + num, TestTable.class);
    }

    /**
     * 仅测试用，注入不需要set
     *
     * @return
     */
    public SyncDaoExecutor getSyncDaoExecutor() {
        return syncDaoExecutor;
    }


    /**
     * 注入异步AsyncDaoExecutor
     */
    @InjectDao(dbName = AppConstant.DB_MYSQL)
    AsyncDaoExecutor asyncDaoExecutor;

    /**
     * 仅测试用，注入不需要set
     *
     * @return
     */
    public AsyncDaoExecutor getAsyncDaoExecutor() {
        return asyncDaoExecutor;
    }


}
