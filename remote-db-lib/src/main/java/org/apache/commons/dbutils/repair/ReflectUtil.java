package org.apache.commons.dbutils.repair;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 反射工具类
 *
 * 覆写，由于android中没有这个类
 *
 * @author Kelly
 * @version 1.0.0
 * @filename ReflectUtil.java
 * @time 2019/7/12 17:02
 * @copyright(C) 2019 song
 */
public class ReflectUtil {

    public static boolean isPackageAccessible(Class<?> var0) {
        try {
            checkPackageAccess(var0);
            return true;
        } catch (SecurityException var2) {
            return false;
        }
    }

    public static void checkPackageAccess(Class<?> var0) {
        checkPackageAccess(var0.getName());
        if(isNonPublicProxyClass(var0)) {
            checkProxyPackageAccess(var0);
        }

    }

    public static void checkPackageAccess(String var0) {
        SecurityManager var1 = System.getSecurityManager();
        if(var1 != null) {
            String var2 = var0.replace('/', '.');
            int var3;
            if(var2.startsWith("[")) {
                var3 = var2.lastIndexOf(91) + 2;
                if(var3 > 1 && var3 < var2.length()) {
                    var2 = var2.substring(var3);
                }
            }

            var3 = var2.lastIndexOf(46);
            if(var3 != -1) {
                var1.checkPackageAccess(var2.substring(0, var3));
            }
        }

    }

    public static boolean isNonPublicProxyClass(Class<?> var0) {
        String var1 = var0.getName();
        int var2 = var1.lastIndexOf(46);
        String var3 = var2 != -1?var1.substring(0, var2):"";
        return Proxy.isProxyClass(var0) && !var3.equals("com.sun.proxy");
    }

    public static void checkProxyPackageAccess(Class<?> var0) {
        SecurityManager var1 = System.getSecurityManager();
        if(var1 != null && Proxy.isProxyClass(var0)) {
            Class[] var2 = var0.getInterfaces();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Class var5 = var2[var4];
                checkPackageAccess(var5);
            }
        }

    }


    /**
     * java反射bean的get方法
     *
     * @param objectClass
     * @param fieldName
     * @return
     */
    public static Method getGetMethod(Class objectClass, String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            return objectClass.getMethod(sb.toString());
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * java反射bean的set方法
     *
     * @param objectClass
     * @param fieldName
     * @return
     */
    public static Method getSetMethod(Class objectClass, String fieldName) {
        try {
            Class[] parameterTypes = new Class[1];
            Field field = objectClass.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            Method method = objectClass.getMethod(sb.toString(), parameterTypes);
            return method;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
