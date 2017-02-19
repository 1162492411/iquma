package com.iquma.dao;

import com.iquma.pojo.Operation;

import java.util.List;

public interface OperationMapper {

    int insertSelective(Operation record);

    List selectsByCondition(Operation condition);

}