package com.sjl.remotedb.db;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.thoughtworks.xstream.XStream;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * DbConfig xml解析器
 *
 * @author Kelly
 * @version 1.0.0
 * @filename DbXmlParser.java
 * @time 2019/7/11 15:19
 * @copyright(C) 2019 song
 */
public class DbXmlParser {
    private static final String TAG = "DbXmlParser";

    /**
     * 解析 assets目录下db-config.xml文件
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static List<DbConfig> parseAsList(Context context) throws Exception {
        InputStream inputStream = context.getAssets().open("db-config.xml");
        return parseAsList(inputStream);
    }

    /**
     * 解析inputStream为 List<DbConfig>
     *
     * @param inputStream 输入流
     * @return
     * @throws Exception
     */
    public static List<DbConfig> parseAsList(InputStream inputStream) throws Exception {
        XStream xStream = new XStream();
        xStream.processAnnotations(DbXmlBean.class);
        DbXmlBean dbXmlBean = (DbXmlBean) xStream.fromXML(inputStream);
        List<DbXmlBean.DbSource> dbSources = dbXmlBean.getDbSources();
        if (dbSources == null || dbSources.isEmpty()) {
            throw new RuntimeException("parse xml failed,node db-source doesn't exist.");
        }
        List<DbConfig> dbConfigList = new ArrayList<>();
        for (DbXmlBean.DbSource dbSource : dbSources) {
            DbConfig dbConfig = new DbConfig();
            dbConfig.setDbName(dbSource.getDbName());
            dbConfig.setActive(dbSource.isActive());
            Class dbClz = dbConfig.getClass();
            List<DbXmlBean.DbSource.Property> properties = dbSource.getProperties();
            if (properties != null && !properties.isEmpty()) {
                for (DbXmlBean.DbSource.Property property : properties) {
                    Field field = dbClz.getDeclaredField(property.getName());
                    field.setAccessible(true);
                    String text = property.getValue();
                    if (TextUtils.isEmpty(text)) {
                        Log.w(TAG, property.getName() + " value is null.");
                        continue;
                    }
                    String typeName = field.getType().getName();
                    if (typeName.contains("String")) {
                        field.set(dbConfig, text);
                    } else if (typeName.contains("int")) {
                        field.set(dbConfig, Integer.parseInt(text));
                    } else if (typeName.contains("long")) {
                        field.set(dbConfig, Long.parseLong(text));
                    } else if (typeName.contains("float")) {
                        field.set(dbConfig, Float.parseFloat(text));
                    } else if (typeName.contains("double")) {
                        field.set(dbConfig, Double.parseDouble(text));
                    } else if (typeName.contains("Integer")) {
                        field.set(dbConfig, Integer.valueOf(text));
                    } else if (typeName.contains("Long")) {
                        field.set(dbConfig, Long.valueOf(text));
                    } else if (typeName.contains("Float")) {
                        field.set(dbConfig, Float.valueOf(text));
                    } else if (typeName.contains("Double")) {
                        field.set(dbConfig, Double.valueOf(text));
                    } else if (typeName.contains("Date")) {
                        Date date = null;
                        if (text.contains("CST")) {
                            date = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK).parse(text);
                        } else {
                            //格式化
                            try {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                date = sdf.parse(text);
                            } catch (ParseException e) {
                                Log.e(TAG, "date convert error:" + property.getName(), e);
                            }
                        }
                        field.set(dbConfig, date);
                    }
                }
                dbConfigList.add(dbConfig);
            } else {
                throw new RuntimeException("parse xml failed,node property doesn't exist.");
            }
        }
        return dbConfigList;
    }
}
