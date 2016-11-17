package com.iquma.dao;

import com.iquma.pojo.Type;

import java.util.List;

public interface TypeMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List selectAll();//获取所有类别
}