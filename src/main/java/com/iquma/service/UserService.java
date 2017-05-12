package com.iquma.service;

import java.util.List;

import com.iquma.pojo.User;

public interface UserService {

    boolean insert(User user);//添加用户

    boolean delete(String id);//删除用户

    boolean update(User record);//更新用户信息

    boolean updatePrestige(String id,Integer prestige);//更新用户威望

    boolean updateRid(String id, Byte rid);//更新用户角色等级

    boolean changeStatus(String id);//封禁用户

    User selectById(String id);//根据id获取用户

    List selectAll();//获取所有用户
}
