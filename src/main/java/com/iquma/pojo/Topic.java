package com.iquma.pojo;

import java.util.Date;

public class Topic {
    private Integer id;

    private String title;

    private Byte sid;

    private Byte tid;

    private String aid;

    private Date addTime;

    private Date reTime;

    private Integer viewCount;

    private Integer rateCount;

    private Boolean isBlock;

    private String content;

    public Topic() {
    }

//    public Topic(String title, String sid, String tid, String aid,Date addTime, Date reTime, String content) {
//        this.title = title;
//        this.sid = Byte.parseByte(sid);
//        this.tid = Byte.parseByte(tid);
//        this.aid = aid;
//        this.addTime = addTime;
//        this.reTime = reTime;
//        this.content = content;
//    }

    public Topic(Integer id, String title, Byte sid, Byte tid, String aid, Date addTime, Date reTime, Integer viewCount, Integer rateCount, Boolean isBlock, String content) {
        this.id = id;
        this.title = title;
        this.sid = sid;
        this.tid = tid;
        this.aid = aid;
        this.addTime = addTime;
        this.reTime = reTime;
        this.viewCount = viewCount;
        this.rateCount = rateCount;
        this.isBlock = isBlock;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Byte getSid() {
        return sid;
    }

    public void setSid(Byte sid) {
        this.sid = sid;
    }

    public Byte getTid() {
        return tid;
    }

    public void setTid(Byte tid) {
        this.tid = tid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
    }

    public Date getaddTime() {
        return addTime;
    }

    public void setaddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getReTime() {
        return reTime;
    }

    public void setReTime(Date reTime) {
        this.reTime = reTime;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getRateCount() {
        return rateCount;
    }

    public void setRateCount(Integer rateCount) {
        this.rateCount = rateCount;
    }

    public Boolean getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Boolean isBlock) {
        this.isBlock = isBlock;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String toString() {
        return "[话题id: " + id + ",标题: " + title + ",标签id: " + tid + ",作者id: " + aid + ",发表时间: " + addTime
                + ",话题内容: " + content + ",最后回复时间: " + reTime + ",浏览量: " + viewCount + ",评价分数: " + rateCount
                + ",话题状态 :" + isBlock;
    }
}