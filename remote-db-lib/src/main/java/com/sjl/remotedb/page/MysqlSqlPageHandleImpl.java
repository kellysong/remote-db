package com.sjl.remotedb.page;

/**
 * mysql数据库的分页实现
 *
 * @author Kelly
 * @version 1.0.0
 * @filename MysqlSqlPageHandleImpl.java
 * @time 2019/10/14 14:31
 * @copyright(C) 2019 song
 */
public class MysqlSqlPageHandleImpl extends SqlPageHandle {


    public MysqlSqlPageHandleImpl(String oldSQL, int pageNo, int pageSize) {
        super(oldSQL, pageNo, pageSize);
    }

    @Override
    public String handlerPagingSQL() {
        StringBuffer sql = new StringBuffer(oldSQL);
        if (pageSize > 0) {
            pageNo = pageNo <= 0 ? 1 : pageNo;
            int startIndex = (pageNo - 1) * pageSize;
            if (startIndex <= 0) {
                sql.append(" limit ").append(pageSize);
            } else {
                sql.append(" limit ").append(startIndex).append(",").append(pageSize);
            }
        }
        return sql.toString();
    }

}
