package com.sjl.remotedb.dao;

import android.text.TextUtils;
import android.util.Log;

import com.sjl.remotedb.annotation.InjectDao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DaoExecutor注解处理器
 *
 * @author Kelly
 * @version 1.0.0
 * @filename DaoExecutorAnnotationProcessor.java
 * @time 2019/7/16 10:37
 * @copyright(C) 2019 song
 */
public class DaoExecutorAnnotationProcessor {

    /**
     * 存储单例对象
     */
    private static Map<String, Object> singletonBeanFactory = new ConcurrentHashMap<>();


    /**
     * 批量处理类上成员变量的InjectDao注解
     *
     * @param objects 任意实例，包括Activity,Fragment,Dao层，MVP的P层等
     * @throws Exception
     */
    public static void processAnnotations(final Object... objects) throws Exception {
        if (objects == null || objects.length == 0) {
            throw new RuntimeException("daoClass is null.");
        }
        for (Object obj : objects) {
            if (obj == null) {
                throw new RuntimeException("inject obj is null.");
            }
            Class clz = obj.getClass();
            Field[] declaredFields = clz.getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                Field field = declaredFields[i];
                if (field.isAnnotationPresent(InjectDao.class)) {
                    InjectDao injectDao = field.getAnnotation(InjectDao.class);
                    String dbName = injectDao.dbName();
                    boolean async = injectDao.isAsync();
                    String scope = injectDao.scope();
                    if (TextUtils.isEmpty(dbName)) {
                        Log.e("Error", "inject dao failed,dbName is null.dbName:" + dbName);
                    } else {
                        Class<?> type = field.getType();
                        String name = type.getName();
                        if (async && name.contains("AsyncDaoExecutor")) { //异步
                            injectDao(obj, field, dbName, scope);
                        } else if (!async && name.contains("SyncDaoExecutor")) {//同步
                            injectDao(obj, field, dbName, scope);
                        } else {
                            throw new RuntimeException("DaoExecutor and async don't match.");
                        }
                    }

                }

            }
        }

    }

    /**
     * 把对象注入dao上（赋值）
     *
     * @param obj
     * @param field
     * @param dbName
     * @param scope
     * @throws Exception
     */
    private static void injectDao(Object obj, Field field, String dbName, String scope) throws Exception {
        field.setAccessible(true);
        Class<?> type = field.getType();
        String name = type.getName();
        String key = dbName + ":" + name;
        if ("singleton".equals(scope)) {
            Object singleton = singletonBeanFactory.get(key);
            if (singleton == null) {
                Method method = type.getMethod("init", String.class);
                Object ret = method.invoke(null, dbName);//得到属性的具体对象
                field.set(obj, ret);
                singletonBeanFactory.put(key, ret);
            } else {
                //根据name获取对象并返回
                field.set(obj, singleton);
            }

        } else {
            Method method = type.getMethod("init", String.class);
            Object ret = method.invoke(null, dbName);//得到属性的具体对象
            field.set(obj, ret);
        }
    }


    /**
     * 清理方法，用于释放资源
     */
    public static void clear() {
        singletonBeanFactory.clear();
        singletonBeanFactory = null;
    }

}
