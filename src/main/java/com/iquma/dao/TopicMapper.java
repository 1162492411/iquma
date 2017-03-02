package com.iquma.dao;


import com.iquma.pojo.Topic;
import java.util.List;

public interface TopicMapper {

    Topic selectByPrimaryKey(Integer id);

    int insert(Topic record);

    int insertSelective(Topic record);

    int updateByPrimaryKey(Topic record);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKeyWithBLOBs(Topic record);

    int increaseReply(Integer id);//增加主贴回复数

    int reduceReply(Integer id);//减少主贴回复数

    int adopt(Integer id);//主贴采纳回复

    int deleteByPrimaryKey(Integer id);

    List selectAll();//获取所有主贴

    boolean changeStatusByPrimaryKey(Integer id);//改变主贴状态

    Topic selectByCondition(Topic record);//获取符合条件的主贴

    List selectsByCondition(Topic record);//获取符合条件的主贴列表


}