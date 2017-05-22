package com.iquma.pojo;

public class Tag {
    private Byte id;

    private Byte pid;

    private String name;

    private String path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }



    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"pid\":" + pid +
                ", \"name\":\"" + name + "\"" +
                ", \"path\":\"" + path + "\"" +
                "}";
    }
}