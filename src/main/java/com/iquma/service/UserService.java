package com.iquma.service;

import com.iquma.pojo.User;
import org.apache.shiro.authc.UnknownAccountException;

public interface UserService {

    boolean insert(User user);//添加用户

    boolean delete(String id);//删除用户

    boolean update(User record);//更新用户信息

    boolean updatePrestige(String id,Integer prestige);//更新用户威望

    boolean updateRid(String id, Byte rid);//更新用户角色等级

    boolean changeStatus(String id);//封禁用户

    User selectSimpleUser(String id) throws UnknownAccountException ;//根据id获取用户的简略信息

    User selectDetailUser(String id) throws UnknownAccountException ;//根据id获取用户的详细信息

}
