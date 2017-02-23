package com.shenchao.bos.utils;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 封装分页信息
 * Created by shenchao on 2016/11/25.
 */
public class PageBean {
    private int currentPage;
    private int pageSize;
    private DetachedCriteria detachedCriteria;//离线条件查询对象
    private int total;
    private List rows;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
