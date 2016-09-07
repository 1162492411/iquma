package com.iquma.dao;

import com.iquma.pojo.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}