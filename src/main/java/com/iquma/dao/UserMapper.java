package com.iquma.dao;

import com.iquma.pojo.User;
import java.util.ArrayList;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    boolean blockByPrimaryKey(String id);

    ArrayList getAllUsers();
}