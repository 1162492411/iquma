package com.iquma.pojo;

import java.util.Date;

public class Notification {
    private Integer id;

    private String uid;

    private String content;

    private Date ntftime;

    private Boolean isnew;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getNtftime() {
        return ntftime;
    }

    public void setNtftime(Date ntftime) {
        this.ntftime = ntftime;
    }

    public Boolean getIsnew() {
        return isnew;
    }

    public void setIsnew(Boolean isnew) {
        this.isnew = isnew;
    }

    public String toString() {
        return "[记录id : " + id + ",通知用户 : " + uid + ",通知内容 : " + content +
                ",通知时间 : " + ntftime + ",通知状态 : " + isnew + "]";
    }

}