package com.sjl.dbtest;

import android.widget.TextView;

import com.sjl.remotedb.dao.AsyncDaoExecutor;
import com.sjl.remotedb.dao.ExecutorCallback;
import com.sjl.remotedb.dao.SyncDaoExecutor;
import com.sjl.util.LogUtils;

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
public class TestTableDao {
    /**
     * 常规方法实例化BaseDaoExecutor
     */
    private SyncDaoExecutor syncDaoExecutor;

    private AsyncDaoExecutor asyncDaoExecutor;

    public void setSyncDaoExecutor(SyncDaoExecutor syncDaoExecutor) {
        this.syncDaoExecutor = syncDaoExecutor;
    }


    public void setAsyncDaoExecutor(AsyncDaoExecutor asyncDaoExecutor) {
        this.asyncDaoExecutor = asyncDaoExecutor;
    }


    public List<TestTable> query(int num) {
        return syncDaoExecutor.queryBeanList("select * from test_table  limit  0," + num, TestTable.class);
    }

    public void queryAsync(final TextView textView, int num) {
        asyncDaoExecutor.queryBeanList("select * from test_table  limit  0," + num, TestTable.class, new ExecutorCallback<List<TestTable>>() {
            @Override
            public void onSuccess(List<TestTable> testTables) {
                LogUtils.i("testTables:" + testTables);
                textView.setText(testTables.toString());
            }

            @Override
            public void onFailed(Throwable e) {

            }
        });
    }
}
