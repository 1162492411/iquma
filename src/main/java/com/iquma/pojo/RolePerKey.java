package com.iquma.pojo;

public class RolePerKey {

    private Byte id;

    private Byte rid;

    private Byte pid;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public Byte getRid() {
        return rid;
    }

    public void setRid(Byte rid) {
        this.rid = rid;
    }

    public Byte getPid() {
        return pid;
    }

    public void setPid(Byte pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "RolePerKey{" +
                "id=" + id +
                ", rid=" + rid +
                ", pid=" + pid +
                '}';
    }
}