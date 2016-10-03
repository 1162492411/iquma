package com.iquma.pojo;

import java.util.Date;

public class Attachment {
    private Integer id;

    private Integer tid;

    private String uid;

    private Date addtime;

    private String filename;

    private Double filesize;

    private String attpath;

    private String description;

    private Byte price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Double getFilesize() {
        return filesize;
    }

    public void setFilesize(Double filesize) {
        this.filesize = filesize;
    }

    public String getAttpath() {
        return attpath;
    }

    public void setAttpath(String attpath) {
        this.attpath = attpath == null ? null : attpath.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Byte getPrice() {
        return price;
    }

    public void setPrice(Byte price) {
        this.price = price;
    }
}