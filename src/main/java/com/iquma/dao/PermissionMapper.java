package com.iquma.dao;

import com.iquma.pojo.Permission;

import java.util.List;

public interface PermissionMapper {

    int deleteByPrimaryKey(Byte id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Byte id);

    Permission selectByCondition(Permission condition);//获取符合条件的权限信息

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List selectAll();//获取所有权限

    List selectByPids(List pids);//获取角色所属的所有权限
}