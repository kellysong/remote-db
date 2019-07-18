package com.sjl.remotedb.db;

/**
 * TODO
 *
 * @author Kelly
 * @version 1.0.0
 * @filename DbCallback.java
 * @time 2019/7/15 13:34
 * @copyright(C) 2019 song
 */
public interface DbCallback {
    void onSuccess();

    void onFailed(Throwable e);
}
