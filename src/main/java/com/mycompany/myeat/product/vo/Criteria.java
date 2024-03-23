package com.mycompany.myeat.product.vo;

import org.springframework.stereotype.Component;

@Component("criteria")
public class Criteria {

    private int page;
    private int perPageNum; 
    private int rowStart;
    private int rowEnd;
    private String keyword;
    private String sortType;

    public Criteria() {
        this.page = 1;
        this.perPageNum = 12;
    }

    public void setPage(int page) {
        if (page <= 0) {
            this.page = 1;
            return;
        }
        this.page = page;
    }

    public void setPerPageNum(int perPageNum) {
        if (perPageNum <= 0 || perPageNum > 100) {
            this.perPageNum = 12;
            return;
        }
        this.perPageNum = perPageNum;
    }

    public int getPage() {
        System.out.println("getPageStart="+getPageStart());
        System.out.println("getRowStart="+getRowStart());
        System.out.println("getRowEnd = " + getRowEnd());
        return page;
    }

    public int getPageStart() {
        int pageStart = (this.page - 1) * perPageNum;
        return pageStart;
    }

    public int getPerPageNum() {
        return this.perPageNum;
    }

    public int getRowStart() {
        rowStart = ((page - 1) * perPageNum) + 1;
        return rowStart;
    }

    public int getRowEnd() {
        rowEnd = rowStart + perPageNum - 1;
        return rowEnd;
    }

    @Override
    public String toString() {
        return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd=" + rowEnd
                + "]";
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
