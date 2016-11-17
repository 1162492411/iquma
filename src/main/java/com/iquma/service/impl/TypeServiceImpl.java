package com.iquma.service.impl;

import com.iquma.dao.TypeMapper;
import com.iquma.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mo on 2016/11/13.
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List selectAll() {
        return this.typeMapper.selectAll();
    }
}
