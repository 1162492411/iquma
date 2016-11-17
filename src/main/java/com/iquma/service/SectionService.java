package com.iquma.service;

import com.iquma.pojo.Section;

import java.util.List;

/**
 * Created by Mo on 2016/11/13.
 */
public interface SectionService {

    List selectAll();//获取所有版块

    Section selectById(Byte id);//根据id获取版块
}
