package com.iquma.service.impl;

import com.iquma.dao.SuclogMapper;
import com.iquma.pojo.Suclog;
import com.iquma.service.SuclogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mo on 2017/2/16.
 */
@Service
public class SuclogServiceImpl implements SuclogService {
    @Autowired
    private SuclogMapper suclogMapper;

    @Override
    public Boolean insert(Suclog record) {
        return this.suclogMapper.insert(record) > 0;
    }

    @Override
    public List selectsByUid(String uid) {
        return this.suclogMapper.selectsByUid(uid);
    }

    @Override
    public Boolean deletesByUid(String uid) {
        return this.deletesByUid(uid);
    }

}
