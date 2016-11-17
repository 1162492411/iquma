package com.iquma.dao;

import com.iquma.pojo.Section;
import com.iquma.pojo.Tag;
import com.iquma.pojo.Topic;
import com.iquma.pojo.Type;

import java.util.ArrayList;
import java.util.List;

public interface TopicMapper {

    Topic selectByPrimaryKey(Integer id);

    int insert(Topic record);

    int insertSelective(Topic record);

    int updateByPrimaryKey(Topic record);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKeyWithBLOBs(Topic record);

    int deleteByPrimaryKey(Integer id);

   // boolean blockByPrimaryKey(Integer id);//关闭主贴--建议废弃

    List selectAll();//获取所有主贴

    boolean changeStatusByPrimaryKey(Integer id);//改变主贴状态--建议替blockByPrimaryKey

    List selectByCondition(Topic record);//获取符合条件的主贴列表


}