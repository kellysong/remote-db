package com.sjl.remotedb.db;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * TODO
 *
 * @author Kelly
 * @version 1.0.0
 * @filename AbstractDataSource.java
 * @time 2019/7/11 17:23
 * @copyright(C) 2019 song
 */
public abstract class AbstractDataSource implements DataSource {



    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }


    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }
}
