package com.sjl.remotedb.dao;

import com.sjl.remotedb.page.Page;
import com.sjl.remotedb.page.SqlPageHandle;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * 数据层接口
 *
 * @author Kelly
 * @version 1.0.0
 * @filename DbConfig.java
 * @time 2019/7/12 15:10
 * @copyright(C) 2019 song
 */
interface IBaseDao<T> {


    /**
     * 查询得到一个bean
     *
     * @param conn   连接
     * @param sql    sql语句
     * @param classT 映射对象class
     * @param params 条件参数
     * @return
     */
    T queryBean(Connection conn, String sql, Class<T> classT, Object... params);


    /**
     * 查询得到一个list集合,有参数
     *
     * @param conn   连接
     * @param sql    sql语句
     * @param classT 映射对象class
     * @param params 条件参数
     * @return
     */
    List<T> queryBeanList(Connection conn, String sql, Class<T> classT, Object... params);


    /**
     * count(*)记录数
     *
     * @param conn   连接
     * @param sql    sql语句
     * @param params 条件参数
     * @return
     */
    long findTotalRecordNum(Connection conn, String sql, Object... params);


    /**
     * 分页查询
     *
     * @param conn          连接
     * @param sqlPageHandle 分页handle
     * @param classT        映射对象class
     * @param params        条件参数
     * @param <T>
     * @return
     */
    <T> Page<T> queryPagination(Connection conn, SqlPageHandle sqlPageHandle, Class<T> classT, Object[] params);


    /**
     * 将查询出结果集中的第一条记录，并封装成Map对象,以数据库的列名为Key，列值为Value
     *
     * @param conn   连接
     * @param sql    sql语句
     * @param params 条件参数
     * @return
     */
    Map<String, Object> queryBeanForMap(Connection conn, String sql, Object... params);


    /**
     * 查询某一条记录，并将指定列的数据转换为Object
     *
     * @param conn       连接
     * @param sql        sql语句
     * @param columnName 列名
     * @param params     条件参数
     * @return
     */
    Object queryBeanColumn(Connection conn, String sql, String columnName, Object... params);


    /**
     * 将查询出的结果集中每一行保存到一个Map对象中，然后将所有Map对象保存到List中
     *
     * @param conn   连接
     * @param sql    sql语句
     * @param params 条件参数
     * @return
     */
    List<Map<String, Object>> queryBeanForListMap(Connection conn, String sql, Object... params);


    /**
     * 更新(update、insert、delete，返回受影响的行数)
     *
     * @param sql    sql语句
     * @param params 条件参数
     * @return true成功 false失败
     */
    boolean update(Connection conn, String sql, Object... params);

    /**
     * 使用事务单个更新(update、insert、delete，返回受影响的行数)
     *
     * @param conn
     * @param sql    sql语句
     * @param params 条件参数
     * @return true成功 false失败
     */
    boolean updateInTx(Connection conn, String sql, Object... params);


    /**
     * 使用事务批量更新(update、insert、delete，返回受影响的行数)
     *
     * @param conn   连接
     * @param sql    sql语句
     * @param params 条件参数
     * @return true成功 false失败
     */
    boolean batchUpdateInTx(Connection conn, String sql, Object[][] params);

    /**
     * 使用事务批量删除
     *
     * @param conn 连接
     * @param sql  sql语句
     * @param ids  删除的id
     */
    void batchDeleteInTx(Connection conn, String sql, String[] ids);

}
