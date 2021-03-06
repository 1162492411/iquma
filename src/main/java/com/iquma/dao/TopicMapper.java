package com.iquma.dao;


import com.iquma.pojo.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicMapper {

    int insert(Topic record);//插入一条主贴

    int deleteByPrimaryKey(Integer id);//删除主贴

    int updateByPrimaryKeySelective(Topic record);//更新主贴

    boolean changeStatusByPrimaryKey(Integer id);//改变主贴状态

    int increaseReply(Integer id);//增加主贴回复数

    int reduceReply(Integer id);//减少主贴回复数

    void increaseViewCount(Integer id);//增加主贴浏览量

    int adopt(Integer id);//主贴采纳回复

    Topic selectByPrimaryKey(Integer id);//根据id选择主贴

    List selectsRevelant(String id);//获取指定用户的所有主贴

    Integer selectsCount(Topic record);//获取符合条件的主贴简略信息的数量--用于主贴列表

    List<Topic> selectsByPage(@Param("start")int start, @Param("top")Topic record);//获取符合条件的主贴简略信息列表--用于主贴列表
}