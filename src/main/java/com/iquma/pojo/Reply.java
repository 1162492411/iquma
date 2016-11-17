package com.iquma.pojo;

import java.util.Date;

public class Reply {
    private Integer id;

    private Integer tid;

    private String uid;

    private Date addTime;

    private Boolean isBest;

    private Boolean isBlock;

    private Integer rateCount;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Boolean getIsBest() {
        return isBest;
    }

    public void setIsBest(Boolean isBest) {
        this.isBest = isBest;
    }

    public Boolean getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Boolean block) {
        isBlock = block;
    }

    public Integer getRateCount() {
        return rateCount;
    }

    public void setRateCount(Integer rateCount) {
        this.rateCount = rateCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", tid=" + tid +
                ", uid='" + uid + '\'' +
                ", addTime=" + addTime +
                ", isBest=" + isBest +
                ", isBlock=" + isBlock +
                ", rateCount=" + rateCount +
                ", content='" + content + '\'' +
                '}';
    }

    //初始化默认回复
    public void parseDefaultReply(){
        addTime = new Date();
    }
}