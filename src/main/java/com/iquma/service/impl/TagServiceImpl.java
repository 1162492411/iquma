package com.iquma.service.impl;

import com.iquma.dao.TagMapper;
import com.iquma.exception.NoSuchTagException;
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
    public Tag selectByCondition(Tag condition) throws NoSuchTagException {
        Tag tag = this.tagMapper.selectByCondition(condition);
        if(null == tag) throw new NoSuchTagException();
        return tag;
    }

    @Override
    public List selectsFirstTag() {
        return this.tagMapper.selectsFirstTag();
    }

    @Override
    public List selectsChildren(Tag condition) {
        return this.tagMapper.selectsByCondition(condition);
    }

    @Override
    public List selectAll() {
        return this.tagMapper.selectAll();
    }

    @Override
    public List selectsRelevant(Byte id) {
        return this.tagMapper.selectsRelevant(id);
    }
}
