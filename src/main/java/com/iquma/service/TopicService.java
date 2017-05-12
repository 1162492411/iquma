package com.iquma.service;

import com.iquma.pojo.Topic;

import java.util.List;

/**
 * Created by Mo on 2016/9/27.
 */
public interface TopicService {

    boolean insert(Topic topic);//新增主题

    boolean deleteById(Integer id);//根据id删除主题

    boolean update(Topic topic);//更新主题信息

    boolean rate(Topic topic);//更新主贴评分

    boolean changeStatus(Integer id);//根据id关闭主贴

    boolean increaseReply(Integer id);//将主题回复数加1

    boolean reduceReply(Integer id);//将主题回复数减1

    boolean adopt(Integer id);//主题采纳回复

    Topic selectById(Integer id);//根据id获取主题

    Topic selectByCondition(Topic condition);//根据条件获取主题

    List selectsByCondition(Topic condition);//根据条件获取主题集合

    List selectsByConditionAndPage(int page, Topic condition);//根据条件和页码获取主题集合

    List selectAll();//获取所有主题
}
