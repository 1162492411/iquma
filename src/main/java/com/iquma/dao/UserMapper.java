package com.iquma.dao;

import com.iquma.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int insert(User record);//插入用户

    int insertSelective(User record);//插入用户

    int deleteByPrimaryKey(String id);//删除用户

    int updateByPrimaryKeySelective(User record);//更新用户信息

    int updatePrestige(@Param("id")String id, @Param("prestige")Integer prestige);//更新用户威望

    int updateRid(@Param("id")String id, @Param("rid")Byte rid);//更新用户角色等级

    boolean changeStatusByPrimaryKey(String id);//改变账户状态

    User selectSimpleByPrimaryKey(@Param("id")String id);//获取指定id的账户的简略信息

    User selectDetailByPrimaryKey(@Param("id")String id);//获取指定id的账户的详细信息

}