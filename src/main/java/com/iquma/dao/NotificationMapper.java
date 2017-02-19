package com.iquma.dao;

import com.iquma.pojo.Notification;

import java.util.List;

public interface NotificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Notification record);

    Notification selectByPrimaryKey(Integer id);

    List selectsByCondition(Notification condition);

    Boolean read(Notification record);
}