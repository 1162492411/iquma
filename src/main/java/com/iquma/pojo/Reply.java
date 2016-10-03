package com.iquma.pojo;

import java.util.Date;

public class Reply {
    private Integer id;

    private Integer tid;

    private String uid;

    private Date addtime;

    private Boolean isbest;

    private Integer ratecount;

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

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Boolean getIsbest() {
        return isbest;
    }

    public void setIsbest(Boolean isbest) {
        this.isbest = isbest;
    }

    public Integer getRatecount() {
        return ratecount;
    }

    public void setRatecount(Integer ratecount) {
        this.ratecount = ratecount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}