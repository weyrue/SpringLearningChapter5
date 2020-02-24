package com.zy.mysql.mybatis.page;

import org.apache.ibatis.session.RowBounds;

import java.util.List;

@SuppressWarnings("unused")
public class Page<T> extends RowBounds {
    public static final int NOT_PAGING_LIMIT = 0;
    private int pageNo = 1;
    private int pageSize = 10;
    private List<T> rows;
    private int totalRecords;
    private int totalPages;

    public int getOffset() {
        if (pageNo < 1) return 0;
        return pageSize * (pageNo - 1);
    }

    public int getLimit() {
        return pageSize;
    }

    public Page() {
        super(0, 10);
    }

    public Page(int pageNo, int pageSize) {
        super((pageSize > 0 ? pageSize : 10) * ((pageNo > 0 ? pageNo : 1) - 1), pageSize > 0 ? pageSize : 10);
        this.pageNo = pageNo > 0 ? pageNo : 1;
        this.pageSize = pageSize > 0 ? pageSize : 10;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
