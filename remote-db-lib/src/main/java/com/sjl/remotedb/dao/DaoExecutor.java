package com.sjl.remotedb.dao;

import com.sjl.remotedb.page.Page;
import com.sjl.remotedb.page.SqlPageHandle;

import java.util.List;
import java.util.Map;

/**
 * DaoExecutor
 *
 * @author Kelly
 * @version 1.0.0
 * @filename DaoExecutor.java
 * @time 2019/7/16 11:46
 * @copyright(C) 2019 song
 */
interface DaoExecutor {


    /**
     * 根据sql语句查询映射成bean
     *
     * @param sql    sql语句
     * @param classT 映射对象class
     * @param params 条件参数
     * @param <T>
     * @return
     */
    <T> T queryBean(String sql, Class<T> classT, Object... params);


    /**
     * 根据sql语句查询映射成List
     *
     * @param sql    sql语句
     * @param classT 映射对象class
     * @param params 条件参数
     * @param <T>
     * @return
     */
    <T> List<T> queryBeanList(String sql, Class<T> classT, Object... params);

    /**
     * 查询总记录数
     *
     * @param sql    sql语句
     * @param params 条件参数
     * @return
     */
    long findTotalRecordNum(String sql, Object... params);


    /**
     * 分页查询
     *
     * @param sqlPageHandle 分页handle
     * @param classT   映射对象class
     * @param params   条件参数
     * @param <T>
     * @return
     */
    <T> Page<T> queryPagination(SqlPageHandle sqlPageHandle, Class<T> classT, Object[] params);


    /**
     * 将查询出结果集中的第一条记录，并封装成Map对象,以数据库的列名为Key，列值为Value
     *
     * @param sql    sql语句
     * @param params 条件参数
     * @return
     */
    Map<String, Object> queryBeanForMap(String sql, Object... params);


    /**
     * 查询某一条记录，并将指定列的数据转换为Object
     *
     * @param sql        sql语句
     * @param columnName 列名
     * @param params     条件参数
     * @return
     */
    Object queryBeanColumn(String sql, String columnName, Object... params);


    /**
     * 将查询出的结果集中每一行保存到一个Map对象中，然后将所有Map对象保存到List中
     *
     * @param sql    sql语句
     * @param params 条件参数
     * @return
     */
    List<Map<String, Object>> queryBeanForListMap(String sql, Object... params);

    /**
     * 更新，修改，删除操作
     *
     * @param sql    sql语句
     * @param params 条件参数
     * @return
     */
    boolean update(String sql, Object... params);

    /**
     * 带事务更新，修改，删除操作
     *
     * @param sql    sql语句
     * @param params 条件参数
     * @return
     */
    boolean updateInTx(String sql, Object... params);

    /**
     * 带事务批量更新，修改，删除操作
     *
     * @param sql    sql语句
     * @param params 条件参数
     * @return
     */
    boolean batchUpdateInTx(String sql, Object[][] params);

    /**
     * 带事务根据id批量删除操作
     *
     * @param sql sql语句
     * @param ids id
     */
    void batchDeleteInTx(String sql, String[] ids);
}
