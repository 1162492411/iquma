package com.iquma.service.impl;

import com.iquma.dao.SectionMapper;
import com.iquma.pojo.Section;
import com.iquma.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mo on 2016/11/13.
 */
@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionMapper sectionMapper;

    @Override
    public List selectAll() {
        return this.sectionMapper.selectAll();
    }

    @Override
    public Section selectById(Byte id) {
        return this.sectionMapper.selectByPrimaryKey(id);
    }
}
