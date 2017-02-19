package com.iquma.service;

import com.iquma.pojo.Topic;

import java.util.List;

/**
 * Created by Mo on 2016/9/27.
 */
public interface TopicService {

    List selectAll();//获取所有主题

    Topic selectById(Integer id);//根据id获取主题

    Topic selectByCondition(Topic condition);//根据条件获取主题

    List selectsByCondition(Topic condition);//根据条件获取主题集合

    List selectsByConditionAndPage(int page, Topic condition);//根据条件和页码获取主题集合

    boolean update(Topic topic);//更新主题信息

    boolean insert(Topic topic);//新增主题

    boolean deleteById(Integer id);//根据id删除主题

    boolean changeStatus(Integer id);//根据id关闭主贴

}
