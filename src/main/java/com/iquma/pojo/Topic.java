package com.iquma.pojo;

import com.iquma.utils.ENUMS;

import java.util.Date;

public class Topic {
    private Integer id;

    private User user;

    private Tag tag;

    private Attachment attachment;

    private String title;

    private String section;

    private Byte tid;

    private String aid;

    private Integer attid;

    private Date addTime;

    private Date reTime;

    private Integer viewCount;

    private Double likeCount;

    private Double hateCount;

    private Byte rateCount;

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

    public void setSection(String section) {
        this.section = section;
    }

    public String getSection() {
        return section;
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

    public Integer getAttid() {
        return attid;
    }

    public void setAttid(Integer attid) {
        this.attid = attid;
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


    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
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

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", user=" + user +
                ", tag=" + tag +
                ", section=" + section +
                ", attachment=" + attachment +
                ", title='" + title + '\'' +
                ", section=" + section +
                ", tid=" + tid +
                ", aid='" + aid + '\'' +
                ", attid=" + attid +
                ", addTime=" + addTime +
                ", reTime=" + reTime +
                ", viewCount=" + viewCount +
                ", likeCount=" + likeCount +
                ", hateCount=" + hateCount +
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

    public Topic(Integer id, Double likeCount, Double hateCount, Byte rateCount) {
        this.id = id;
        this.likeCount = likeCount;
        this.hateCount = hateCount;
        this.rateCount = rateCount;
    }

    //初始化默认教程主贴
    public void parseDefaultTutorial(){
        section = ENUMS.SECTION_TUTORIAL;
        addTime = new Date();
        reTime = new Date();
    }

    //初始化默认提问主贴
    public void parseDefaultDiscuss(){
        section = ENUMS.SECTION_DISCUSS;
        addTime = new Date();
    }

    //初始化默认经验主贴
    public void parseDefaultArticle(){
        section = ENUMS.SECTION_ARTICLE;
        addTime = new Date();
    }


    //初始化默认代码主贴
    public void parseDefaultCode(){
        section = ENUMS.SECTION_CODE;
        addTime = new Date();
    }



}

