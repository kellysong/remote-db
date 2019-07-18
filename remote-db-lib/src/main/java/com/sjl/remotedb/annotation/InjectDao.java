package com.sjl.remotedb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * InjectDao
 *
 * @author Kelly
 * @version 1.0.0
 * @filename InjectDao.java
 * @time 2019/7/16 10:29
 * @copyright(C) 2019 song
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectDao {
    /**
     * 数据源db名字
     * @return
     */
    String dbName() default "";

    /**
     *dao执行器异步还是同步，默认异步
     * @return
     */
    boolean isAsync() default true;

    /**
     * 作用域，默认单例singleton，否则多例prototype
     * @return
     */
    String scope() default "singleton";

}
