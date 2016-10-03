package com.iquma.pojo;

public class WeightKey {
    private String uid;

    private Byte typeid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Byte getTypeid() {
        return typeid;
    }

    public void setTypeid(Byte typeid) {
        this.typeid = typeid;
    }

    public String toString() {
        return "[账户 : " + uid + ",类别 : " + typeid + "";
    }
}