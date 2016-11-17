package com.iquma.service.impl;

import com.iquma.dao.TagMapper;
import com.iquma.pojo.Tag;
import com.iquma.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mo on 2016/11/13.
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List selectTagsByPid(Tag condition) {
        return this.tagMapper.selectByCondition(condition);
    }
}
