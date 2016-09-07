package com.iquma.pojo;

public class RolePerKey {
    private Byte rid;

    private Byte pid;

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
    
    public String toString(){
    	return "[角色id : " + rid + ",权限id : " + pid + "]";
    }
}