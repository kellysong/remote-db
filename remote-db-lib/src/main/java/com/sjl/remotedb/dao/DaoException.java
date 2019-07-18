package com.sjl.remotedb.dao;

/**
 * TODO
 *
 * @author Kelly
 * @version 1.0.0
 * @filename DaoException.java
 * @time 2019/7/16 18:03
 * @copyright(C) 2019 song
 */
public class DaoException extends RuntimeException {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
