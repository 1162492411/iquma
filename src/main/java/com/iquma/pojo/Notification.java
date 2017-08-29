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

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"uid\":\"" + uid + '\"' +
                ", \"content\":\"" + content + '\"' +
                ", \"ntftime\":\"" + ntftime + '\"' +
                ", \"isnew\":" + isnew +
                '}';
    }

    public Notification() {
    }

    public Notification(String uid, String content, Date ntftime, Boolean isnew) {
        this.uid = uid;
        this.content = content;
        this.ntftime = ntftime;
        this.isnew = isnew;
    }
}