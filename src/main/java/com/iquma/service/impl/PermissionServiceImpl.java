package com.iquma.service.impl;

import com.iquma.dao.PermissionMapper;
import com.iquma.pojo.Permission;
import com.iquma.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mo on 2016/11/22.
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List selectAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public Permission selectById(Byte id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public Permission selectByCondition(Permission condition) {
        return permissionMapper.selectByCondition(condition);
    }

    @Override
    public List selectByPids(List pids) {
        return permissionMapper.selectByPids(pids);
    }
}
