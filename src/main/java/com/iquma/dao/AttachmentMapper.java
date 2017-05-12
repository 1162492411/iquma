package com.iquma.dao;

import com.iquma.pojo.Attachment;

public interface AttachmentMapper {

    int insertSelective(Attachment record);//插入附件

    int deleteByPrimaryKey(Integer id);//删除附件

    int updateByPrimaryKeySelective(Attachment record);//修改附件信息

    Attachment selectByPrimaryKey(Integer id);//根据id查找附件

}