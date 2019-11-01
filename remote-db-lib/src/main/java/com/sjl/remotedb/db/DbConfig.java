package com.sjl.remotedb.db;

import android.text.TextUtils;

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
    private static final int KEEP_ALIVE_TIME = 10 * 1000;

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

    public DbConfig(Builder builder) {
        this.dbName = builder.dbName;
        this.active = builder.active;
        this.driverClass = builder.driverClass;
        this.jdbcUrl = builder.jdbcUrl;
        this.username = builder.username;
        this.password = builder.password;
        this.initialPoolSize = builder.initialPoolSize;
        this.maxPoolSize = builder.maxPoolSize;
        this.keepAliveTime = builder.keepAliveTime;

    }

    public String getDbName() {
        return dbName;
    }

    public String getDriverClass() {
        return driverClass;
    }


    public String getJdbcUrl() {
        return jdbcUrl;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public int getInitialPoolSize() {
        return initialPoolSize;
    }


    public int getMaxPoolSize() {
        return maxPoolSize;
    }


    public int getKeepAliveTime() {
        return keepAliveTime;
    }


    public boolean isActive() {
        return active;
    }


    public static final class Builder {
        private String dbName;
        private boolean active;
        private String driverClass;
        private String jdbcUrl;
        private String username;
        private String password;
        private int initialPoolSize;
        private int maxPoolSize;
        private int keepAliveTime;

        public Builder setDbName(String dbName) {
            checkNull("dbName", dbName);
            this.dbName = dbName;
            return this;
        }

        public Builder setActive(boolean active) {
            this.active = active;
            return this;
        }

        public Builder setDriverClass(String driverClass) {
            checkNull("driverClass", driverClass);
            this.driverClass = driverClass;
            return this;
        }

        public Builder setJdbcUrl(String jdbcUrl) {
            checkNull("jdbcUrl", jdbcUrl);
            this.jdbcUrl = jdbcUrl;
            return this;
        }

        public Builder setUsername(String username) {
            checkNull("username", username);
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            checkNull("password", password);
            this.password = password;
            return this;
        }


        public Builder setInitialPoolSize(int initialPoolSize) {
            if (initialPoolSize <= 0) {
                this.initialPoolSize = INITIAL_POOL_SIZE;
            } else {
                this.initialPoolSize = initialPoolSize;
            }
            return this;
        }

        public Builder setMaxPoolSize(int maxPoolSize) {
            if (maxPoolSize <= 0) {
                this.maxPoolSize = MAX_POOL_SIZE;
            } else {
                this.maxPoolSize = maxPoolSize;
            }
            return this;
        }

        public Builder setKeepAliveTime(int keepAliveTime) {
            if (keepAliveTime <= 0) {
                this.keepAliveTime = KEEP_ALIVE_TIME;
            } else {
                this.keepAliveTime = keepAliveTime;
            }
            return this;
        }

        public DbConfig build() {
            return new DbConfig(this);
        }
    }


    private static void checkNull(String msg, String str) {
        if (TextUtils.isEmpty(str)) {
            throw new NullPointerException(msg + " can't be null.");
        }
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
