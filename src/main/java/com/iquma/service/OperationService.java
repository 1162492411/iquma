package com.iquma.service;

import com.iquma.pojo.Operation;

import java.util.List;

/**
 * Created by Mo on 2017/1/26.
 */
public interface OperationService {

    Boolean insert(Operation record);//存储一条用户操作记录

    List selectsByCondition(Operation condition);//获取符合条件的操作记录集合

    String selectsTopicRateInfo(Operation condition);//获取用户对主贴的投票信息

    String selectsReplyRateInfo(Operation condition);//获取用户对回复的投票信息


}
