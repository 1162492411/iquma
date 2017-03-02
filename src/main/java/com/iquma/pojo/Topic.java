package com.iquma.pojo;

import java.util.Date;

public class Topic {
    private Integer id;

    private User user;

    private Tag tag;

    private Section section;

    private String title;

    private Byte sid;

    private Byte tid;

    private String aid;

    private Date addTime;

    private Date reTime;

    private Integer viewCount;

    private Integer rateCount;

    private Integer replyCount;

    private Boolean isBlock;

    private Boolean hasBest;

    private String content;


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

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
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

    public Boolean getHasBest() {
        return hasBest;
    }

    public void setHasBest(Boolean hasBest) {
        this.hasBest = hasBest;
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

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", user=" + user +
                ", tag=" + tag +
                ", section=" + section +
                ", title='" + title + '\'' +
                ", sid=" + sid +
                ", tid=" + tid +
                ", aid='" + aid + '\'' +
                ", addTime=" + addTime +
                ", reTime=" + reTime +
                ", viewCount=" + viewCount +
                ", rateCount=" + rateCount +
                ", replyCount=" + replyCount +
                ", isBlock=" + isBlock +
                ", hasBest=" + hasBest +
                ", content='" + content + '\'' +
                '}';
    }

    public Topic() {
    }

    public Topic(Integer id) {
        this.id = id;
    }

    //初始化默认教程主贴
    public void parseDefaultTutorial(){
        sid = Byte.valueOf("1");
        addTime = new Date();
        reTime = new Date();
    }

    //初始化默认提问主贴
    public void parseDefaultDiscuss(){
        sid = Byte.valueOf("2");
        addTime = new Date();
        reTime = new Date();
    }

    //初始化默认经验主贴
    public void parseDefaultArticle(){
        sid = Byte.valueOf("3");
        addTime = new Date();
        reTime = new Date();
    }


    //初始化默认代码主贴
    public void parseDefaultCode(){
        sid = Byte.valueOf("4");
        addTime = new Date();
        reTime = new Date();
    }



}

