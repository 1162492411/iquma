package com.iquma.service.impl;

import com.iquma.dao.RoleMapper;
import com.iquma.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List getAllRoles() {
        return this.roleMapper.selectAll();
    }


}
