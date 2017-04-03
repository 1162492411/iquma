package com.iquma.pojo;

import java.util.Date;

public class Attachment {
    private Integer id;

    private String uid;

    private Date addtime;

    private String name;

    private Long size;

    private String path;

    private Byte price;

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

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Byte getPrice() {
        return price;
    }

    public void setPrice(Byte price) {
        this.price = price;
    }

    public Attachment() {
    }

    public Attachment(String uid, Date addtime, String name, Long size, String path) {
        this.uid = uid;
        this.addtime = addtime;
        this.name = name;
        this.size = size;
        this.path = path;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", addtime=" + addtime +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", path='" + path + '\'' +
                ", price=" + price +
                '}';
    }
}