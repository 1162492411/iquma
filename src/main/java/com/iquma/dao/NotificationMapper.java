package com.iquma.dao;

import com.iquma.pojo.Notification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NotificationMapper {

    int insert(Notification record);//插入通知

    Boolean read(Notification record);//将通知标为已读

    Integer selectsCount(Notification condition);//获取符合条件的通知的总数

    List selectsByPage(@Param("start")int start, @Param("ntf")Notification condition);//按页获取符合条件的通知列表
}