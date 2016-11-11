package com.iquma.service.impl;

import com.iquma.dao.TopicMapper;
import com.iquma.pojo.Section;
import com.iquma.pojo.Tag;
import com.iquma.pojo.Topic;
import com.iquma.pojo.Type;
import com.iquma.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Mo on 2016/9/27.
 */
@Service
public class TopicServiceImpl implements TopicService {


    @Resource
    private TopicMapper topicMapper;


    @Override
    public ArrayList getAllTopics() {
        return this.topicMapper.getAllTopics();
    }

    @Override
    public Topic selectTopicById(Integer id) {
        return this.topicMapper.selectByPrimaryKey(id);
    }

    @Override
    public Topic selectTopicByCondition(Topic topic) {
        return this.topicMapper.selectTopicByCondition(topic);
    }

    @Override
    public ArrayList selectTopicsByCondition(Topic topic) {
        return this.topicMapper.selectTopicsByCondition(topic);
    }


    @Override
    public boolean updateTopic(Topic topic) {
        return this.topicMapper.updateByPrimaryKeySelective(topic) > 0;
    }

    @Override
    public boolean insertTopic(Topic topic) {
        return this.topicMapper.insertSelective(topic) > 0;
    }

    @Override
    public boolean deleteTopicById(Integer id) {
        return this.topicMapper.deleteByPrimaryKey(id) > 0 ;
    }


}
