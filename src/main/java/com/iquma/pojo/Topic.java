package com.iquma.pojo;

import com.iquma.utils.ENUMS;

import java.util.Date;

public class Topic {

    private Integer id;

    private User user;

    private Tag tag;

    private Attachment attachment;

    private String title;

    private String sec;

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

    private Integer hasBest;

    private Boolean noReply;

    private Boolean isHigh;

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

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getSec() {
        return sec;
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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
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

    public Integer getHasBest() { return hasBest; }

    public void setHasBest(Integer hasBest) { this.hasBest = hasBest; }

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

    public Boolean getBlock() {
        return isBlock;
    }

    public void setBlock(Boolean block) {
        isBlock = block;
    }

    public Boolean getNoReply() {
        return noReply;
    }

    public void setNoReply(Boolean noReply) {
        this.noReply = noReply;
    }

    public Boolean getIsHigh() {
        return isHigh;
    }

    public void setIsHigh(Boolean high) {
        isHigh = high;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"user\":" + user +
                ", \"tag\":" + tag +
                ", \"attachment\":" + attachment +
                ", \"title\":\"" + title + "\"" +
                ", \"sec\":\"" + sec + "\"" +
                ", \"tid\":" + tid +
                ", \"aid\":\"" + aid + "\"" +
                ", \"attid\":" + attid +
                ", \"addTime\":\"" + addTime + "\"" +
                ", \"reTime\":\"" + reTime + "\"" +
                ", \"viewCount\":" + viewCount +
                ", \"likeCount\":" + likeCount +
                ", \"hateCount\":" + hateCount +
                ", \"rateCount\":" + rateCount +
                ", \"replyCount\":" + replyCount +
                ", \"isBlock\":" + isBlock +
                ", \"hasBest\":" + hasBest +
                ", \"noReply\":" + noReply +
                ", \"isHigh\":" + isHigh +
                ", \"content\":\"" + content + "\"" +
                "}";
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

    public Topic(String title, String sec, Byte tid, String aid, Integer attid, Date addTime, Date reTime, Integer viewCount, Double likeCount, Double hateCount, Byte rateCount, Integer replyCount, Boolean isBlock, Integer hasBest, String content) {
        this.title = title;
        this.sec = sec;
        this.tid = tid;
        this.aid = aid;
        this.attid = attid;
        this.addTime = addTime;
        this.reTime = reTime;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.hateCount = hateCount;
        this.rateCount = rateCount;
        this.replyCount = replyCount;
        this.isBlock = isBlock;
        this.hasBest = hasBest;
        this.content = content;
    }

    //初始化默认添加主贴
    public void parseDefaultTopic(){
        viewCount = ENUMS.TOPIC_DEFAULT_VIEWCOUNT;
        likeCount = ENUMS.TOPIC_DEFAULT_LIKECOUNT;
        hateCount = ENUMS.TOPIC_DEFAULT_HATECOUNT;
        rateCount = ENUMS.TOPIC_DEFAULT_RATECOUNT;
        replyCount = ENUMS.TOPIC_DEFAULT_REPLYCOUNT;
        attid = ENUMS.TOPIC_DEFAULT_ATTID;
        isBlock = ENUMS.TOPIC_DEFAULT_ISBLOCK;
        hasBest = ENUMS.TOPIC_DEFAULT_HASBEST;
        noReply = ENUMS.TOPIC_DEFAULT_NOREPLY;
        isHigh = ENUMS.TOPIC_DEFAULT_ISHIGH;
    }

    //初始化默认教程主贴
    public void parseDefaultTutorial(){
        parseDefaultTopic();
        sec = ENUMS.SEC_TUTORIAL;
    }

    //初始化默认提问主贴
    public void parseDefaultQuestion(){
        parseDefaultTopic();
        sec = ENUMS.SEC_QUESTION;
    }

    //初始化默认经验主贴
    public void parseDefaultArticle(){
        parseDefaultTopic();
        sec = ENUMS.SEC_ARTICLE;
    }

    //初始化默认代码主贴
    public void parseDefaultCode(){
        parseDefaultTopic();
        sec = ENUMS.SEC_CODE;
    }



}

