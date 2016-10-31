package com.iquma.service;

import com.iquma.pojo.Reply;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mo on 2016/10/4.
 */
public interface ReplyService {
    List selectReplyByTid(Integer tid);//查找主贴的所有回复

    Boolean insert(Reply record);//发表回复

    Boolean deleteById(Integer id);//删除指定id的回复

    Boolean update(Reply record);//更新回复


}
