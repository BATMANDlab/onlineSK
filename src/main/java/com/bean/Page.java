package com.bean;

import java.util.List;

public class Page<T> {

    private int index;
    private int pageSize;
    private int totalPage;
    private int totalCount;
    private int pageNum;
    private List<T> data;

    @Override
    public String toString() {
        return "Page{" +
                "index=" + index +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", pageNum=" + pageNum +
                ", data=" + data +
                '}';
    }

    public int getIndex() {
        return (getPageNum()-1)*getPageSize();
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return (int) Math.ceil(1.0*getTotalCount()/getPageSize());
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {

        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNum() {
        if (pageNum > getTotalPage()){
            return getTotalPage();
        }
        if (pageNum < 1){
            return 1;
        }
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
