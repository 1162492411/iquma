package com.iquma.dao;

import com.iquma.pojo.Role;

import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Map getAllRoles();
}