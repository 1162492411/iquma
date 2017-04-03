package com.iquma.service;

import java.util.List;

import com.iquma.pojo.User;

public interface UserService {

    User selectById(String id);//根据id获取用户

    List selectAll();//获取所有用户

    boolean validatorUserPass(String id, String pass);//验证用户密码

    boolean validatorIsAdmin(String id);//验证用户是否为管理员

    boolean validatorEmail(String id, String email);//验证用户安全邮箱

    boolean validatorStatus(String id);//验证用户状态

    boolean changeStatus(String id);//封禁用户

    boolean update(User record);//更新用户信息

    boolean insert(User user);//添加用户

    boolean delete(String id);//删除用户

    boolean updatePrestige(String id,Integer prestige);//更新用户威望

    boolean updateRid(String id, Byte rid);//更新用户角色等级
}
