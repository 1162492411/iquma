package com.iquma.dao;

import com.iquma.pojo.Tag;

public interface TagMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
}