package com.iquma.dao;

import com.iquma.pojo.Permission;

import java.util.List;

public interface PermissionMapper {

    Permission selectByPermission(String condition);//获取符合条件的权限信息

    List selectByPids(List pids);//获取角色所属的所有权限
}