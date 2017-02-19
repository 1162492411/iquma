package com.iquma.pojo;

import java.util.Date;

public class Operation {
    private Integer id;

    private String uid;

    private String opid;

    private Byte pid;

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

    public String getOpid() {
        return opid;
    }

    public void setOpid(String opid) {
        this.opid = opid;
    }

    public Byte getPid() {
        return pid;
    }

    public void setPid(Byte pid) {
        this.pid = pid;
    }

    public Date getOptime() {
        return optime;
    }

    public void setOptime(Date optime) {
        this.optime = optime;
    }

    public String toString() {
        return "[记录id : " + id + ",操作人id : " + uid + ",操作对象id : " + opid + ",所用权限 : " + pid
                + ",操作时间 : " + optime + "]";
    }

    public Operation() {
    }

    public Operation(String uid, String opid, Byte pid, Date optime) {
        this.uid = uid;
        this.opid = opid;
        this.pid = pid;
        this.optime = optime;
    }
}