package com.iquma.pojo;

public class User {
    private static final String defaultAvatar = "http://ocgfh1n3q.bkt.clouddn.com/default_avatar.png";
    private static final Boolean defaultIsBlock = false;
    private static final Integer defaultPrestige = 0;
    private static final Integer defaultAppCount = 0;

    private String id;

    private Byte rid;

    private String name;

    private String pass;

    private String avatar;

    private String email;

    private Boolean isBlock;

    private Integer prestige;

    private Integer appCount;

    private String salt;

    public User() {

    }

    public User(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public User(String id, String name, String email) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(String id, Byte rid, String name, String pass, String avatar,
                String email, Boolean isBlock, Integer prestige, Integer appCount) {
        super();
        this.id = id;
        this.rid = rid;
        this.name = name;
        this.pass = pass;
        this.avatar = avatar;
        this.email = email;
        this.isBlock = isBlock;
        this.prestige = prestige;
        this.appCount = appCount;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Byte getRid() {
        return rid;
    }

    public void setRid(Byte rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass == null ? null : pass.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Boolean getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Boolean isBlock) {
        this.isBlock = isBlock;
    }

    public Integer getPrestige() {
        return prestige;
    }

    public void setPrestige(Integer prestige) {
        this.prestige = prestige;
    }

    public Integer getAppCount() {
        return appCount;
    }

    public void setAppCount(Integer appCount) {
        this.appCount = appCount;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    //默认添加用户模板类
    public void parseDefaultAddUser(){
        this.setIsBlock(defaultIsBlock);
        this.setPrestige(defaultPrestige);
        this.setAppCount(defaultAppCount);
        this.setAvatar(null == this.getAvatar() ? defaultAvatar : this.getAvatar());
        this.setPass(this.getId().substring(this.getId().length() - 6, this.getId().length()));
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", rid=" + rid +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", isBlock=" + isBlock +
                ", prestige=" + prestige +
                ", appCount=" + appCount +
                ", salt='" + salt + '\'' +
                '}';
    }
}