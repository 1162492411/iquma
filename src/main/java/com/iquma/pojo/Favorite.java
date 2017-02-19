package com.iquma.pojo;

import java.util.Date;

public class Favorite {
    private Integer id;

    private String uid;

    private Integer obid;

    private Date favTime;

    private Topic topic;

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

    public Integer getObid() {
        return obid;
    }

    public void setObid(Integer obid) {
        this.obid = obid;
    }

    public Date getFavTime() {
        return favTime;
    }

    public void setFavTime(Date favtime) {
        this.favTime = favtime;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", obid=" + obid +
                ", favTime=" + favTime +
                ", topic=" + topic +
                '}';
    }
}