package com.iquma.service.impl;

import com.iquma.dao.RolePerMapper;
import com.iquma.service.RolePerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mo on 2016/11/29.
 */
@Service
public class RolePerServiceImpl implements RolePerService{
    @Autowired
    private RolePerMapper rolePerMapper;

    @Override
    public List selectPersByRid(Byte rid) {
        return this.rolePerMapper.selectPersByRid(rid);
    }
}
