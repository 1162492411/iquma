package com.iquma.dao;

import com.iquma.pojo.Notification;

import java.util.List;

public interface NotificationMapper {

    int insertSelective(Notification record);//插入通知

    int deleteByPrimaryKey(Integer id);//删除通知

    Boolean read(Notification record);//将通知标为已读

    Notification selectByPrimaryKey(Integer id);//按id获取通知

    List selectsByCondition(Notification condition);//获取符合条件的通知列表
}