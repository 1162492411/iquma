package com.iquma.service.impl;

import com.iquma.dao.AttachmentMapper;
import com.iquma.pojo.Attachment;
import com.iquma.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mo on 2017/3/16.
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public boolean insert(Attachment record) {
        return this.attachmentMapper.insertSelective(record) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return this.attachmentMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean update(Attachment attachment) {
        return this.attachmentMapper.updateByPrimaryKeySelective(attachment) > 0;
    }

    @Override
    public Attachment selectById(Integer id) {
        return this.attachmentMapper.selectByPrimaryKey(id);
    }
}
