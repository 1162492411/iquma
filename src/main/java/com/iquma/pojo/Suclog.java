package com.iquma.pojo;

import java.util.Date;

public class Suclog {
    private Integer id;

    private String uid;

    private Date logintime;

    private String loginip;

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

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip == null ? null : loginip.trim();
    }
    
    public String toString(){
    	return "[记录id : " + id + ",账户id : " + uid + ",登录时间 : " + logintime + ",登录IP : " + loginip + "]";
    }
}