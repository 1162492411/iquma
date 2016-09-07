package com.iquma.pojo;

import java.util.Date;

public class Favorite {
    private Integer id;

    private String uid;

    private Integer obid;

    private Date favtime;

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

    public Date getFavtime() {
        return favtime;
    }

    public void setFavtime(Date favtime) {
        this.favtime = favtime;
    }
    
    public String toString(){
    	return "[记录id : " + id + ",用户id : " + uid + ",话题id : " + obid + ",收藏时间 : "+ favtime + "]";
    }
}