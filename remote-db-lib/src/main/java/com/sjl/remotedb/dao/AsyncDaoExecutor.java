package com.sjl.remotedb.dao;

import com.sjl.remotedb.page.Page;
import com.sjl.remotedb.page.SqlPageHandle;
import com.sjl.remotedb.util.AsyncTaskUtils;
import com.sjl.remotedb.util.Callback;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 异步执行器AsyncDaoExecutor,不同数据源new 一个
 * <p>1.以下方法都是异步执行</p>
 *
 * @author Kelly
 * @version 1.0.0
 * @filename SyncDaoExecutor.java
 * @time 2019/7/16 11:49
 * @copyright(C) 2019 song
 */
public class AsyncDaoExecutor {

    private final SyncDaoExecutor syncDaoExecutor;

    private AsyncDaoExecutor(String dbName) {
        this.syncDaoExecutor = new SyncDaoExecutor(dbName);
    }

    /**
     * 包装同步执行器
     *
     * @param syncDaoExecutor
     */
    private AsyncDaoExecutor(SyncDaoExecutor syncDaoExecutor) {
        this.syncDaoExecutor = syncDaoExecutor;
    }


    /**
     * 初始化dao执行器
     *
     * @param dbName 数据源名字
     * @return
     */
    public static AsyncDaoExecutor init(String dbName) {
        return new AsyncDaoExecutor(dbName);
    }

    /**
     * 初始化dao执行器
     *
     * @param syncDaoExecutor 同步执行器
     * @return
     */
    public static AsyncDaoExecutor init(SyncDaoExecutor syncDaoExecutor) {
        return new AsyncDaoExecutor(syncDaoExecutor);
    }


    /**
     * 根据sql语句查询映射成bean
     *
     * @param sql
     * @param classT
     * @param executorCallback
     * @param params
     * @param <T>
     */
    public <T> void queryBean(final String sql, final Class<T> classT, final ExecutorCallback<T> executorCallback, final Object... params) {
        AsyncTaskUtils.doAsync2(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return syncDaoExecutor.queryBean(sql, classT, params);
            }
        }, new Callback<T>() {
            @Override
            public void accept(T result) {
                if (executorCallback != null) {
                    executorCallback.onSuccess(result);
                }
            }
        }, new Callback<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                if (executorCallback != null) {
                    executorCallback.onFailed(throwable);
                }
            }
        });
    }

    /**
     * 根据sql语句查询映射成List
     *
     * @param sql
     * @param classT
     * @param executorCallback
     * @param params
     * @param <T>
     */
    public <T> void queryBeanList(final String sql, final Class<T> classT, final ExecutorCallback<List<T>> executorCallback, final Object... params) {
        AsyncTaskUtils.doAsync2(new Callable<List<T>>() {
            @Override
            public List<T> call() throws Exception {
                return syncDaoExecutor.queryBeanList(sql, classT, params);
            }
        }, new Callback<List<T>>() {
            @Override
            public void accept(List<T> result) {
                if (executorCallback != null) {
                    executorCallback.onSuccess(result);
                }
            }
        }, new Callback<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                if (executorCallback != null) {
                    executorCallback.onFailed(throwable);
                }
            }
        });
    }

    /**
     * 查询总记录数
     *
     * @param sql
     * @param executorCallback
     * @param params
     */
    public void findTotalRecordNum(final String sql, final ExecutorCallback<Long> executorCallback, final Object... params) {
        AsyncTaskUtils.doAsync2(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return syncDaoExecutor.findTotalRecordNum(sql, params);
            }
        }, new Callback<Long>() {
            @Override
            public void accept(Long result) {
                if (executorCallback != null) {
                    executorCallback.onSuccess(result);
                }
            }
        }, new Callback<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                if (executorCallback != null) {
                    executorCallback.onFailed(throwable);
                }
            }
        });
    }

    /**
     * 分页查询
     *
     * @param sqlPageHandle
     * @param classT
     * @param executorCallback
     * @param params
     * @param <T>
     * @return
     */
    public <T> void queryPagination(final SqlPageHandle sqlPageHandle, final Class<T> classT, final ExecutorCallback<Page<T>> executorCallback, final Object... params) {
        AsyncTaskUtils.doAsync2(new Callable<Page<T>>() {
            @Override
            public Page<T> call() throws Exception {
                return syncDaoExecutor.queryPagination(sqlPageHandle, classT, params);
            }
        }, new Callback<Page<T>>() {
            @Override
            public void accept(Page<T> result) {
                if (executorCallback != null) {
                    executorCallback.onSuccess(result);
                }
            }
        }, new Callback<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                if (executorCallback != null) {
                    executorCallback.onFailed(throwable);
                }
            }
        });
    }


    /**
     * 将查询出结果集中的第一条记录，并封装成Map对象,以数据库的列名为Key，列值为Value
     *
     * @param sql
     * @param executorCallback
     * @param params
     */
    public void queryBeanForMap(final String sql, final ExecutorCallback<Map<String, Object>> executorCallback, final Object... params) {

        AsyncTaskUtils.doAsync2(new Callable<Map<String, Object>>() {
            @Override
            public Map<String, Object> call() throws Exception {
                return syncDaoExecutor.queryBeanForMap(sql, params);
            }
        }, new Callback<Map<String, Object>>() {
            @Override
            public void accept(Map<String, Object> result) {
                if (executorCallback != null) {
                    executorCallback.onSuccess(result);
                }
            }
        }, new Callback<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                if (executorCallback != null) {
                    executorCallback.onFailed(throwable);
                }
            }
        });
    }

    /**
     * 查询某一条记录，并将指定列的数据转换为Object
     *
     * @param sql
     * @param columnName
     * @param executorCallback
     * @param params
     */
    public void queryBeanColumn(final String sql, final String columnName, final ExecutorCallback<Object> executorCallback, final Object... params) {
        AsyncTaskUtils.doAsync2(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return syncDaoExecutor.queryBeanColumn(sql, columnName, params);
            }
        }, new Callback<Object>() {
            @Override
            public void accept(Object result) {
                if (executorCallback != null) {
                    executorCallback.onSuccess(result);
                }
            }
        }, new Callback<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                if (executorCallback != null) {
                    executorCallback.onFailed(throwable);
                }
            }
        });
    }

    /**
     * 将查询出的结果集中每一行保存到一个Map对象中，然后将所有Map对象保存到List中
     *
     * @param sql
     * @param executorCallback
     * @param params
     */
    public void queryBeanForListMap(final String sql, final ExecutorCallback<List<Map<String, Object>>> executorCallback, final Object... params) {

        AsyncTaskUtils.doAsync2(new Callable<List<Map<String, Object>>>() {
            @Override
            public List<Map<String, Object>> call() throws Exception {
                return syncDaoExecutor.queryBeanForListMap(sql, params);
            }
        }, new Callback<List<Map<String, Object>>>() {
            @Override
            public void accept(List<Map<String, Object>> result) {
                if (executorCallback != null) {
                    executorCallback.onSuccess(result);
                }
            }
        }, new Callback<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                if (executorCallback != null) {
                    executorCallback.onFailed(throwable);
                }
            }
        });
    }


    /**
     * 更新，修改，删除操作
     *
     * @param sql
     * @param executorCallback
     * @param <T>
     */
    public <T> void update(final String sql, final ExecutorCallback<Boolean> executorCallback, final Object... params) {
        AsyncTaskUtils.doAsync2(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return syncDaoExecutor.update(sql, params);
            }
        }, new Callback<Boolean>() {
            @Override
            public void accept(Boolean result) {
                if (executorCallback != null) {
                    executorCallback.onSuccess(result);
                }
            }
        }, new Callback<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                if (executorCallback != null) {
                    executorCallback.onFailed(throwable);
                }
            }
        });
    }


    /**
     * 带事务更新，修改，删除操作
     *
     * @param sql
     * @param executorCallback
     * @param <T>
     */
    public <T> void updateInTx(final String sql, final ExecutorCallback<Boolean> executorCallback, final Object... params) {
        AsyncTaskUtils.doAsync2(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return syncDaoExecutor.updateInTx(sql, params);
            }
        }, new Callback<Boolean>() {
            @Override
            public void accept(Boolean result) {
                if (executorCallback != null) {
                    executorCallback.onSuccess(result);
                }
            }
        }, new Callback<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                if (executorCallback != null) {
                    executorCallback.onFailed(throwable);
                }
            }
        });
    }


    /**
     * 带事务批量更新，修改，删除操作
     *
     * @param sql
     * @param executorCallback
     * @param <T>
     */
    public <T> void batchUpdateInTx(final String sql, final Object[][] params, final ExecutorCallback<Boolean> executorCallback) {
        AsyncTaskUtils.doAsync2(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return syncDaoExecutor.batchUpdateInTx(sql, params);
            }
        }, new Callback<Boolean>() {
            @Override
            public void accept(Boolean result) {
                if (executorCallback != null) {
                    executorCallback.onSuccess(result);
                }
            }
        }, new Callback<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                if (executorCallback != null) {
                    executorCallback.onFailed(throwable);
                }
            }
        });
    }


    /**
     * 带事务根据id批量删除操作
     *
     * @param sql
     * @param executorCallback
     * @param <T>
     */
    public <T> void batchDeleteInTx(final String sql, final String[] ids, final ExecutorCallback<Boolean> executorCallback) {
        AsyncTaskUtils.doAsync2(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                syncDaoExecutor.batchDeleteInTx(sql, ids);
                return true;
            }
        }, new Callback<Boolean>() {
            @Override
            public void accept(Boolean result) {
                if (executorCallback != null) {
                    executorCallback.onSuccess(result);
                }
            }
        }, new Callback<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                if (executorCallback != null) {
                    executorCallback.onFailed(throwable);
                }
            }
        });
    }

}
