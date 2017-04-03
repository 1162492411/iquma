package com.iquma.dao;

import com.iquma.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    boolean changeStatusByPrimaryKey(String id);//改变账户状态

    List selectAll();//获取所有用户

    List selectByCondition(User condition);//获取符合条件的账户列表

    int updatePrestige(@Param("id")String id, @Param("prestige")Integer prestige);//更新用户威望

    int updateRid(@Param("id")String id, @Param("rid")Byte rid);//更新用户角色等级

}