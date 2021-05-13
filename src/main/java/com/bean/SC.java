package com.bean;

public class SC {
    private int scid;
    private int sid;
    private int cid;
    private double score;

    @Override
    public String toString() {
        return "SC{" +
                "scid=" + scid +
                ", sid=" + sid +
                ", cid=" + cid +
                ", score=" + score +
                '}';
    }

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
