package com.iquma.service.impl;

import com.github.pagehelper.PageHelper;
import com.iquma.dao.TopicMapper;
import com.iquma.pojo.Topic;
import com.iquma.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Mo on 2016/9/27.
 */
@Service
public class TopicServiceImpl implements TopicService {


    @Resource
    private TopicMapper topicMapper;

    @Override
    public Topic selectById(Integer id) {
        System.out.println("调用了topic的selectById方法，传入参数" + id);
        return this.topicMapper.selectByPrimaryKey(id);
    }

    @Override
    public Topic selectByCondition(Topic condition) {
        return this.topicMapper.selectByCondition(condition);
    }


    @Override
    public List selectsByCondition(Topic topic) {
        List result = this.topicMapper.selectsByCondition(topic);
        return  result;
    }

    @Override
    public List selectsByConditionAndPage(int page, Topic condition) {
        PageHelper.startPage(page,10);
        return this.topicMapper.selectsByCondition(condition);
    }

    @Override
    public List<Topic> selectsSimpleByCondition(Topic condition) {
        return this.topicMapper.selectsSimpleByCondition(condition);
    }

    @Override
    public List<Topic> selectsSimpleByConditionAndPage(int page, Topic condition) {
        PageHelper.startPage(page,10);
        return this.topicMapper.selectsSimpleByCondition(condition);
    }

    @Override
    public boolean update(Topic topic) {
        return this.topicMapper.updateByPrimaryKeySelective(topic) > 0;
    }

    @Override
    public boolean increaseReply(Integer id) {
        return this.topicMapper.increaseReply(id) > 0;
    }

    @Override
    public boolean reduceReply(Integer id) {
        return this.topicMapper.reduceReply(id) > 0;
    }

    @Override
    public void increaseViewCount(Integer id) {
        this.topicMapper.increaseViewCount(id);
    }

    @Override
    public boolean adopt(Integer id) {
        return this.topicMapper.adopt(id) > 0;
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

    @Override
    public boolean rate(Topic topic) {
        return this.topicMapper.updateByPrimaryKeySelective(topic) > 0;
    }


}
