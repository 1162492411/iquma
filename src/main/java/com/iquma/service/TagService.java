package com.iquma.service;

import com.iquma.pojo.Tag;

import java.util.List;

/**
 * Created by Mo on 2016/11/13.
 */
public interface TagService {

    List selectTagsByPid(Tag condition);//获取某类型的所有标签

}
