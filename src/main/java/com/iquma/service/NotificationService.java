package com.iquma.service;

import com.iquma.pojo.Notification;

import java.util.List;

/**
 * Created by Mo on 2017/2/16.
 */
public interface NotificationService {

    Boolean insert(Notification record);//存储一条通知信息

    List selectsByCondition(Notification record);//获取符合条件的通知集合

    Boolean read(Notification record);//将通知标记为已读

}
