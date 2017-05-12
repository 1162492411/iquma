package com.iquma.service;

import com.iquma.pojo.Attachment;

/**
 * Created by Mo on 2017/3/16.
 */
public interface AttachmentService {

    boolean insert(Attachment record);//插入附件

    boolean delete(Integer id);//删除附件

    boolean update(Attachment attachment);//修改附件信息

    Attachment selectById(Integer id);//根据id查找附件

}
