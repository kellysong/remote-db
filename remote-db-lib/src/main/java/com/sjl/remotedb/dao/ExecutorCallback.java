package com.sjl.remotedb.dao;

/**
 * TODO
 *
 * @author Kelly
 * @version 1.0.0
 * @filename ExecutorCallback.java
 * @time 2019/7/16 12:23
 * @copyright(C) 2019 song
 */
public interface ExecutorCallback<T> {
    void onSuccess(T t);

    void onFailed(Throwable e);
}
