package com.iquma.pojo;

import java.util.Date;

public class Block {
    private Integer id;

    private String uid;

    private String opid;

    private Date starttime;

    private Date endtime;

    private String reason;

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

    public String getOpid() {
        return opid;
    }

    public void setOpid(String opid) {
        this.opid = opid == null ? null : opid.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
    
    public String toString(){
    	return "[记录id : " + id + ",封禁id : " + uid + "操作者id : " + opid + ",起始时间 : " + starttime
    			 + ",结束时间 : " + endtime + ",封禁原因 : " + reason + "]";
    }
}