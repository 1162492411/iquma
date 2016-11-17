package com.iquma.pojo;

public class Block {
    private Integer id;

    private String uid;

    private String opid;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", opid='" + opid + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}