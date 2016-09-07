package com.iquma.pojo;

public class Role {
    private Byte id;

    private String name;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    
    public String toString(){
    	return "[角色id : " + id + ", 角色名称 : " + name + "]";
    }
}