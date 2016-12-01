package com.iquma.service;

import com.iquma.pojo.Role;

import java.util.List;

public interface RoleService {

    List selectAll();//获取所有角色

    Role selectById(Byte uid);//根据id获取角色
}
