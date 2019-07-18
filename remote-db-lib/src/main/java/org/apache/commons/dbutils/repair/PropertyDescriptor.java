package org.apache.commons.dbutils.repair;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * 属性描述
 * 覆写，由于android中没有这个类
 *
 * @author Kelly
 * @version 1.0.0
 * @filename PropertyDescriptor.java
 * @time 2019/7/12 17:17
 * @copyright(C) 2019 song
 */
public class PropertyDescriptor {
    private Class<?> propertyType;
    private String name;
    private Method writeMethod;
    private Method readMethod;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 基于DBUtils修改，删除了一些无引用代码及精简类的方法
     * 本次修改涉及的类
     * org.apache.commons.dbutils.BeanProcessor
     * org.apache.commons.dbutils.AbstractQueryRunner
     * 设计重写和精简的类
     * org.apache.commons.dbutils.repair.ReflectUtil
     * org.apache.commons.dbutils.repair.PropertyDescriptor
     * @param c
     * @return
     */
    public static PropertyDescriptor[] init(Class<?> c) {
        Field[] fields = c.getDeclaredFields();
        PropertyDescriptor[] propertyDescriptors = new PropertyDescriptor[fields.length];
        for (int i = 0; i < fields.length; i++) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor();
            Field field = fields[i];
            String name = field.getName();
            propertyDescriptor.setName(name);
            propertyDescriptor.setWriteMethod(ReflectUtil.getSetMethod(c, name));
            propertyDescriptor.setReadMethod(ReflectUtil.getGetMethod(c, name));
            propertyDescriptor.setPropertyType(field.getType());
            propertyDescriptors[i] = propertyDescriptor;
        }
        return propertyDescriptors;
    }



    public Method getWriteMethod() {
        return writeMethod;
    }

    public void setWriteMethod(Method writeMethod) {
        this.writeMethod = writeMethod;
    }

    public Method getReadMethod() {
        return readMethod;
    }

    public void setReadMethod(Method readMethod) {
        this.readMethod = readMethod;
    }


    public Class<?> getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Class<?> propertyType) {
        this.propertyType = propertyType;
    }



}
