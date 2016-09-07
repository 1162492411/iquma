package com.iquma.service.impl;

import com.iquma.dao.RoleMapper;
import com.iquma.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * Created by Mo on 2016/8/30.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public Map getAllRoles() {
        return this.roleMapper.getAllRoles();
    }



}
