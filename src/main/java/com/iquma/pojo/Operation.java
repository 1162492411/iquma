package com.iquma.pojo;

import java.util.Date;

public class Operation {
    private Integer id;

    private String uid;

    private String opid;

    private Byte pid;

    private Date opTime;

    private Permission permission;

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

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", opid='" + opid + '\'' +
                ", pid=" + pid +
                ", optime=" + opTime +
                ", permission=" + permission +
                '}';
    }

    public Operation() {
    }

    public Operation(String uid, String opid) {
        this.uid = uid;
        this.opid = opid;
    }

    public Operation(String uid, String opid, Byte pid, Date optime) {
        this.uid = uid;
        this.opid = opid;
        this.pid = pid;
        this.opTime = optime;
    }
}