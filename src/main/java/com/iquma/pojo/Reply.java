package com.iquma.pojo;

import java.util.Date;

public class Reply {
    private Integer id;

    private Integer tid;

    private String title;

    private String uid;

    private Date addTime;

    private Double likeCount;

    private Double hateCount;

    private Byte rateCount;

    private Boolean isBest;

    private Boolean isBlock;

    private String content;

    private User user;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Double getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Double likeCount) {
        this.likeCount = likeCount;
    }

    public Double getHateCount() {
        return hateCount;
    }

    public void setHateCount(Double hateCount) {
        this.hateCount = hateCount;
    }

    public Byte getRateCount() {
        return rateCount;
    }

    public void setRateCount(Byte rateCount) {
        this.rateCount = rateCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", tid=" + tid +
                ", title='" + title + '\'' +
                ", uid='" + uid + '\'' +
                ", addTime=" + addTime +
                ", likeCount=" + likeCount +
                ", hateCount=" + hateCount +
                ", rateCount=" + rateCount +
                ", isBest=" + isBest +
                ", isBlock=" + isBlock +
                ", content='" + content + '\'' +
                ", user=" + user +
                '}';
    }

    public Reply() {
    }

    public Reply(Integer id, Double likeCount, Double hateCount, Byte rateCount) {
        this.id = id;
        this.likeCount = likeCount;
        this.hateCount = hateCount;
        this.rateCount = rateCount;
    }

    //初始化默认回复
    public void parseDefaultReply(){
        addTime = new Date();
    }
}