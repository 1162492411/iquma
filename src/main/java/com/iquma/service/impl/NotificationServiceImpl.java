package com.iquma.service.impl;

import com.iquma.dao.NotificationMapper;
import com.iquma.pojo.Notification;
import com.iquma.service.NotificationService;
import com.iquma.utils.PageUtil;
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
        return this.notificationMapper.insert(record) > 0;
    }

    @Override
    public Boolean read(Notification record) {
        return this.notificationMapper.read(record);
    }

    @Override
    public Integer selectsCount(Notification condition) {
        return this.notificationMapper.selectsCount(condition);
    }

    @Override
    public List selectsByPage(int page, Notification condition) {
        return this.notificationMapper.selectsByPage(PageUtil.getStart(page),condition);
    }
}
