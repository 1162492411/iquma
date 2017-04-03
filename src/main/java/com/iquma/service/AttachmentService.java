package com.iquma.service;

import com.iquma.pojo.Attachment;

/**
 * Created by Mo on 2017/3/16.
 */
public interface AttachmentService {

    boolean insert(Attachment record);//插入附件

    Attachment selectById(Integer id);//根据id查找附件

}
