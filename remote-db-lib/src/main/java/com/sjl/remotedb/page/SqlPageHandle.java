package com.sjl.remotedb.page;

/**
 * 分页接口,根据不同的数据库，调用不同的实现
 *
 * @author Kelly
 * @version 1.0.0
 * @filename SqlPageHandle.java
 * @time 2019/10/14 14:30
 * @copyright(C) 2019 song
 */
public abstract class SqlPageHandle {
    /**
     * 原SQL
     */
    public String oldSQL;
    /**
     * 第几页，用来计算first 这个值由（pageNo-1）*pageSize
     */
    public int pageNo;
    /**
     * 每页数量
     */
    public int pageSize;

    public SqlPageHandle(String oldSQL, int pageNo, int pageSize) {
        this.oldSQL = oldSQL;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    /**
     * 返回新的sql
     *
     * @return
     */
    public abstract String handlerPagingSQL();

}