package com.iquma.service;

import com.iquma.pojo.Permission;

import java.util.List;

/**
 * Created by Mo on 2016/11/22.
 */
public interface PermissionService {

    Permission selectByPermission(String condition);//获取符合条件的权限信息

    List selectByPids(List pids);//获取角色所属的所有权限
}
