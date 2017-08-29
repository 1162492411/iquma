package com.iquma.service;

import com.iquma.pojo.Notification;
import java.util.List;

/**
 * Created by Mo on 2017/2/16.
 */
public interface NotificationService {

    /**
     * 存储一条通知信息
     * @param record 待存储的通知信息
     * @return 是否存储成功
     */
    Boolean insert(Notification record);

    /**
     * 将通知状态更改为已读
     * @param record 待修改的通知
     * @return 是否已将该通知状态修改为已读
     */
    Boolean read(Notification record);

    /**
     * 获取符合条件的通知的总数
     * @param condition 条件参数
     * @return 符合条件的通知的总数
     */
    Integer selectsCount(Notification condition);

    /**
     * //按页数获取符合条件的通知集合
     * @param page 页数
     * @param condition 条件参数
     * @return 符合条件的通知的集合
     */
    List selectsByPage(int page, Notification condition);

}
