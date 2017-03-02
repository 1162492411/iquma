package com.iquma.dao;

import com.iquma.pojo.Section;

import java.util.List;

public interface SectionMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(Section record);

    int insertSelective(Section record);

    Section selectByPrimaryKey(Byte id);

    Section selectByName(String name);

    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKey(Section record);

    List selectAll();//获取所有版块
}