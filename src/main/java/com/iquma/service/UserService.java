package com.iquma.service;

import java.util.ArrayList;

import com.iquma.pojo.User;

public interface UserService {
    User getUserById(String id);//根据id获取用户

    ArrayList getAllUsers();//获取所有用户

    boolean validatorUserPass(String id, String pass);//验证用户密码

    boolean isAdmin(String id);//验证用户是否为管理员

    boolean validatorEmail(String id, String email);//验证用户安全邮箱

    boolean validatorUserStatus(String id);//验证用户状态

    boolean blockUser(String id);//封禁用户

    boolean updateUser(User record);//更新用户信息

    boolean insert(User user);//添加用户
}
