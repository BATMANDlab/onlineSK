package com.bean;

public class Course {
    private int cid;
    private String cname;
    private int tid;
    private int cstock;

    @Override
    public String toString() {
        return "Course{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", tid=" + tid +
                ", cstock=" + cstock +
                '}';
    }

    public int getCstock() {
        return cstock;
    }

    public void setCstock(int cstock) {
        this.cstock = cstock;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }
}
