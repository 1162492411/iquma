package com.iquma.dao;

import com.iquma.pojo.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagMapper {

    Tag selectByPrimaryKey(Byte id);

    List selectAll();//获取所有标签

    Tag selectByCondition(Tag condition);//获取符合条件的标签

    List selectsFirstTag();//获取所有一级标签

    List selectsByCondition(Tag condition);//获取符合条件的标签集合

    List selectsRelevant(Byte id);//查找相关标签

}