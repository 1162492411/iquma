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
    public Permission selectByPermission(String condition) {
        return permissionMapper.selectByPermission(condition);
    }

    @Override
    public List selectByPids(List pids) {
        return permissionMapper.selectByPids(pids);
    }
}
