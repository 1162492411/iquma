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

    //List selectTagsByPid(Byte pid);//根据类别id获取标签列表--建议废弃

    List selectByCondition(Tag condition);//获取符合条件的标签列表--建议替换selectTagsByPid
}