package com.iquma.dao;

import com.iquma.pojo.Attachment;

import java.util.ArrayList;

public interface AttachmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attachment record);

    int insertSelective(Attachment record);

    Attachment selectByPrimaryKey(Integer id);

    Attachment selectAttachmentByCondition(Attachment attachment);//根据条件查找附件 -- 未实现

    ArrayList selectAttachmentsByCondition(Attachment attachment);//根据条件查找附件集合  -- 未实现

    int updateByPrimaryKeySelective(Attachment record);

    int updateByPrimaryKey(Attachment record);
}