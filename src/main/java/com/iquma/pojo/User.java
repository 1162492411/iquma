package com.iquma.pojo;

public class User {
    private String id;

    private Byte rid;

    private String name;

    private String pass;

    private String avatar;

    private String email;

    private Boolean isBlock;

    private Integer prestige;

    private Integer appCount;

    public User(){

    }

    public User(String id, String name){
        super();
        this.id = id ;
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

    public String toString(){
        return "[编号 : " + id + ", 角色 : " + rid + ", 昵称 : " + name + ", 密码 : "
                + pass + ", 头像 : " + avatar + ", 邮箱 : " + email + ", 账户状态 : "
                + isBlock + ", 威望 : " + prestige + ", 获赞 :" + appCount + "]";
    }
}