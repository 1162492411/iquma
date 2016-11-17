package com.iquma.dao;

import com.iquma.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

  //  boolean blockByPrimaryKey(String id);//封禁账户--建议废弃

    boolean changeStatusByPrimaryKey(String id);//改变账户状态--建议替换blockByPrimaryKey

    List selectAll();//获取所有用户

    List selectByCondition(User condition);//获取符合条件的账户列表

}