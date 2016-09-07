package com.iquma.pojo;

import java.util.Date;

public class Operation {
    private Integer id;

    private String uid;

    private String obid;

    private Byte perid;

    private Date optime;

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

    public String getObid() {
        return obid;
    }

    public void setObid(String obid) {
        this.obid = obid == null ? null : obid.trim();
    }

    public Byte getPerid() {
        return perid;
    }

    public void setPerid(Byte perid) {
        this.perid = perid;
    }

    public Date getOptime() {
        return optime;
    }

    public void setOptime(Date optime) {
        this.optime = optime;
    }
    
    public String toString(){
    	return "[记录id : " + id + ",操作人id : " + uid + ",操作对象id : " + obid + ",所用权限 : " + perid 
    			+ ",操作时间 : " + optime + "]";
    }
}