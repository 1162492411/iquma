package com.iquma.dao;

import com.iquma.pojo.Section;

public interface SectionMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(Section record);

    int insertSelective(Section record);

    Section selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKey(Section record);
}