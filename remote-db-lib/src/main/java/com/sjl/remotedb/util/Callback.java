package com.sjl.remotedb.util;

/**
 * TODO
 *
 * @author Kelly
 * @version 1.0.0
 * @filename Callback.java
 * @time 2019/7/16 12:21
 * @copyright(C) 2019 song
 */
public interface Callback<T> {

    /**
     * @param t
     */
    void accept(T t);
}