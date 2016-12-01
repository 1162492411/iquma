package com.iquma.service;

import com.iquma.pojo.Permission;

import java.util.List;

/**
 * Created by Mo on 2016/11/22.
 */
public interface PermissionService {

    List selectAll();//获取所有权限

    Permission selectById(Byte id);//根据id获取权限

    List selectByPids(List pids);//获取角色所属的所有权限
}
