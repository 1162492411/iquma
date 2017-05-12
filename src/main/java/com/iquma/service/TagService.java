package com.iquma.service;

import com.iquma.pojo.Tag;

import java.util.List;

/**
 * Created by Mo on 2016/11/13.
 */
public interface TagService {

    Tag selectByCondition(Tag condition);//获取符合条件的标签

    List selectTagsByCondition(Tag condition);//获取符合条件的标签集合

}
