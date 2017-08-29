package com.iquma.service.impl;

import com.iquma.dao.TopicMapper;
import com.iquma.exception.NoSuchTopicException;
import com.iquma.pojo.Topic;
import com.iquma.service.TopicService;
import com.iquma.utils.PageUtil;
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
    public Topic selectById(Integer id) throws NoSuchTopicException {
        Topic topic = this.topicMapper.selectByPrimaryKey(id);
        if(topic == null) throw new NoSuchTopicException();
        return topic;
    }

    @Override
    public List selectsRevelant(String id) {
        return this.topicMapper.selectsRevelant(id);
    }

    @Override
    public Integer selectsCount(Topic condition) {
        return this.topicMapper.selectsCount(condition);
    }

    @Override
    public List<Topic> selectsByPage(int page, Topic condition) {
        return this.topicMapper.selectsByPage(PageUtil.getStart(page),condition);
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
        return this.topicMapper.insert(topic) > 0;
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
