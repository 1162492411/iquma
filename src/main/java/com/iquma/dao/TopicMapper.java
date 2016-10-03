package com.iquma.dao;

import com.iquma.pojo.Section;
import com.iquma.pojo.Tag;
import com.iquma.pojo.Topic;
import com.iquma.pojo.Type;

import java.util.ArrayList;

public interface TopicMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKeyWithBLOBs(Topic record);

    int updateByPrimaryKey(Topic record);

    ArrayList getAllTopics();//获取所有话题

    Topic selectTopicByCondition(Topic topic);//根据条件获取主题--未实现

    ArrayList selectTopicsByCondition(Topic topic);//根据条件获取主题集合--未实现

}