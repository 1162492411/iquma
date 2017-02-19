package com.iquma.service.impl;

import com.iquma.dao.NotificationMapper;
import com.iquma.pojo.Notification;
import com.iquma.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mo on 2017/2/16.
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public Boolean insert(Notification record) {
        return this.notificationMapper.insertSelective(record) > 0 ;
    }

    @Override
    public List selectsByCondition(Notification record) {
        return this.notificationMapper.selectsByCondition(record);
    }

    @Override
    public Boolean read(Notification record) {
        return this.notificationMapper.read(record);
    }
}
