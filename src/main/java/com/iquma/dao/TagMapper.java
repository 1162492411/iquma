package com.iquma.dao;

import com.iquma.pojo.Tag;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    List selectAll();//获取所有标签

    Tag selectByCondition(Tag condition);//获取符合条件的标签

    List selectTagsByCondition(Tag condition);//获取符合条件的标签集合


}