package com.iquma.service;

import com.iquma.pojo.Section;
import com.iquma.pojo.Tag;
import com.iquma.pojo.Topic;
import com.iquma.pojo.Type;

import java.util.ArrayList;

/**
 * Created by Mo on 2016/9/27.
 */
public interface TopicService {

    ArrayList getAllTopics();//获取所有主题

    Topic selectTopicById(Integer id);//根据id获取主题

    Topic selectTopicByCondition(Topic topic);//根据条件获取主题

    ArrayList selectTopicsByCondition(Topic topic);//根据条件获取主题集合

    boolean updateTopic(Topic topic);//更新主题信息

    boolean insertTopic(Topic topic);//新增主题

    boolean deleteTopicById(Integer id);//根据id删除主题
}
