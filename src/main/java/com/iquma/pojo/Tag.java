package com.iquma.pojo;

public class Tag {
    private Byte id;

    private Byte pid;

    private String name;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public Byte getPid() {
        return pid;
    }

    public void setPid(Byte pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                '}';
    }
}