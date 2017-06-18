package com.iquma.service.impl;

import com.iquma.dao.OperationMapper;
import com.iquma.pojo.Operation;
import com.iquma.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mo on 2017/1/26.
 */
@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationMapper operationMapper;

    @Override
    public Boolean insert(Operation record) {
        return operationMapper.insertSelective(record) > 0;
    }

    @Override
    public List selectsByCondition(Operation condition) {
        return this.operationMapper.selectsByCondition(condition);
    }

    @Override
    public String selectsTopicRateInfo(Operation condition) {
        return this.operationMapper.selectsTopicRateInfo(condition);
    }

    @Override
    public String selectsReplyRateInfo(Operation condition) {
        return this.operationMapper.selectsReplyRateInfo(condition);
    }

}
