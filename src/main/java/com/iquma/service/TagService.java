package com.iquma.service;

import com.iquma.exception.NoSuchTagException;
import com.iquma.pojo.Tag;

import java.util.List;

/**
 * Created by Mo on 2016/11/13.
 */
public interface TagService {

    Tag selectByCondition(Tag condition) throws NoSuchTagException;//获取符合条件的标签

    List selectsFirstTag();//获取所有一级标签

    List selectsChildren(Tag condition);//获取符合条件的标签集合

    List selectAll();//获取所有标签的名称

    List selectsRelevant(Byte id);//获取相关标签

}
