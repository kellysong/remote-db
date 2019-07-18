package com.sjl.remotedb.db;

/**
 * db配置类
 *
 * @author Kelly
 * @version 1.0.0
 * @filename DbConfig.java
 * @time 2019/7/11 14:58
 * @copyright(C) 2019 song
 */
public class DbConfig {
    /**
     * 初始化连接数
     */
    private static final int INITIAL_POOL_SIZE = 3;
    /**
     * 空闲池，最大连接数
     */
    private static final int MAX_POOL_SIZE = 6;
    /**
     * 超时时间（毫秒）
     */
    private static final int KEEP_ALIVE_TIME = 10*1000;

    /**
     * 数据源名字
     */
    private String dbName;
    /**
     * 激活状态
     */
    private boolean active;

    private String driverClass;
    private String jdbcUrl;
    private String username;
    private String password;
    private int initialPoolSize;
    private int maxPoolSize;
    private int keepAliveTime;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getInitialPoolSize() {
        if (initialPoolSize <= 0) {
            return INITIAL_POOL_SIZE;
        }
        return initialPoolSize;
    }

    public void setInitialPoolSize(int initialPoolSize) {
        this.initialPoolSize = initialPoolSize;
    }


    public int getMaxPoolSize() {
        if (maxPoolSize <= 0) {
            return MAX_POOL_SIZE;
        }
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {

        this.maxPoolSize = maxPoolSize;
    }

    public int getKeepAliveTime() {
        if (keepAliveTime <= 0) {
            return KEEP_ALIVE_TIME;
        }
        return keepAliveTime;
    }

    public void setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "DbConfig{" +
                "dbName='" + dbName + '\'' +
                ", active=" + active +
                ", driverClass='" + driverClass + '\'' +
//                ", jdbcUrl='" + jdbcUrl + '\'' +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
                ", initialPoolSize=" + initialPoolSize +
                ", maxPoolSize=" + maxPoolSize +
                ", timeout=" + keepAliveTime +
                '}';
    }
}
