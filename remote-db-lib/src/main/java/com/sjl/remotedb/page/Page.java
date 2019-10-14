package com.sjl.remotedb.page;

import java.util.List;

/**
 * 分页实体
 *
 * @author Kelly
 * @version 1.0.0
 * @filename Page.java
 * @time 2019/10/14 14:32
 * @copyright(C) 2019 song
 */
public class Page<T> {

    //一页显示的记录数
    private int numPerPage;
    //记录总数
    private int totalRows;
    //总页数
    private int totalPages;
    //当前页码
    private int currentPage;
    //起始行数
    private int startIndex;
    //结束行数
    private int lastIndex;
    //结果集存放List
    private List<T> resultList;

    public Page(int currentPage, int numPerPage, List<T> list) {

        //设置每页显示记录数
        setNumPerPage(numPerPage);
        //设置要显示的页数
        setCurrentPage(currentPage);
        //总记录数
//        setTotalRows(list.isEmpty() ? 0 : list.size());
        //计算总页数
        setTotalPages();
        //计算起始行数
        setStartIndex();
        //计算结束行数
        setLastIndex();
        //装入结果集
        setResultList(list);
    }

    public Page(int currentPage, int numPerPage, int totalRows, List<T> list) {

        //设置每页显示记录数
        setNumPerPage(numPerPage);
        //设置要显示的页数
        setCurrentPage(currentPage);
        //总记录数
        setTotalRows(totalRows);
        //计算总页数
        setTotalPages();
        //计算起始行数
        setStartIndex();
        //计算结束行数
        setLastIndex();
        //装入结果集
        setResultList(list);
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages() {
        if (totalRows % numPerPage == 0) {
            this.totalPages = totalRows / numPerPage;
        } else {
            this.totalPages = (totalRows / numPerPage) + 1;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex() {
        this.startIndex = (currentPage - 1) * numPerPage;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex() {
        if (totalRows < numPerPage) {
            this.lastIndex = totalRows;
        } else if ((totalRows % numPerPage == 0) || (totalRows % numPerPage != 0 && currentPage < totalPages)) {
            this.lastIndex = currentPage * numPerPage;
        } else if (totalRows % numPerPage != 0 && currentPage == totalPages) {//最后一页
            this.lastIndex = totalRows;
        }
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }
}