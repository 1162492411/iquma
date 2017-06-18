package com.iquma.dao;

import com.iquma.pojo.Operation;

import java.util.List;

public interface OperationMapper {

    int insertSelective(Operation record);

    List selectsByCondition(Operation condition);

    String selectsTopicRateInfo(Operation condition);//选取用户对主贴的投票行为

    String selectsReplyRateInfo(Operation condition);//选取用户对回复的投票行为
}