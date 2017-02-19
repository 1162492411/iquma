package com.iquma.service.impl;

import com.iquma.dao.TagMapper;
import com.iquma.pojo.Tag;
import com.iquma.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mo on 2016/12/4.
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;



    @Override
    public Tag selectByCondition(Tag condition) {
        return this.tagMapper.selectByCondition(condition);
    }

    @Override
    public List selectTagsByCondition(Tag coditon) {
        return this.tagMapper.selectTagsByCondition(coditon);
    }
}
