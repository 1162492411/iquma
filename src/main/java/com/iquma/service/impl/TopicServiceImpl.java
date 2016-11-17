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
import java.util.List;

/**
 * Created by Mo on 2016/9/27.
 */
@Service
public class TopicServiceImpl implements TopicService {


    @Resource
    private TopicMapper topicMapper;


    @Override
    public List selectAll() {
        return this.topicMapper.selectAll();
    }

    @Override
    public Topic selectById(Integer id) {
        return this.topicMapper.selectByPrimaryKey(id);
    }

    @Override
    public List selectByCondition(Topic topic) {
        return this.topicMapper.selectByCondition(topic);
    }

    @Override
    public boolean update(Topic topic) {
        return this.topicMapper.updateByPrimaryKeySelective(topic) > 0;
    }

    @Override
    public boolean insert(Topic topic) {
        return this.topicMapper.insertSelective(topic) > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.topicMapper.deleteByPrimaryKey(id) > 0 ;
    }

    @Override
    public boolean changeStatus(Integer id) {
        return this.topicMapper.changeStatusByPrimaryKey(id);
    }


}
