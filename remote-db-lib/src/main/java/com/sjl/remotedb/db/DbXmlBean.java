package com.sjl.remotedb.db;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

import java.util.List;

/**
 * db xml映射类
 *
 * @author Kelly
 * @version 1.0.0
 * @filename DbXmlBean.java
 * @time 2019/7/15 9:08
 * @copyright(C) 2019 song
 */
@XStreamAlias("db-config")
public class DbXmlBean {

    @XStreamImplicit(itemFieldName = "db-source")
    private List<DbSource> dbSources;


    public static class DbSource {
        @XStreamAsAttribute()
        @XStreamAlias("dbName")
        private String dbName;

        @XStreamAsAttribute()
        @XStreamAlias("active")
        private boolean active;

        @XStreamImplicit(itemFieldName = "property")
        private List<Property> properties;

        @XStreamConverter(value = ToAttributedValueConverter.class, strings = {"value"})
        public static class Property {
            @XStreamAsAttribute()
            @XStreamAlias("name")
            private String name;
            private String value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public String getDbName() {
            return dbName;
        }

        public void setDbName(String dbName) {
            this.dbName = dbName;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public List<Property> getProperties() {
            return properties;
        }

        public void setProperties(List<Property> properties) {
            this.properties = properties;
        }
    }


    public List<DbSource> getDbSources() {
        return dbSources;
    }

    public void setDbSources(List<DbSource> dbSources) {
        this.dbSources = dbSources;
    }
}
