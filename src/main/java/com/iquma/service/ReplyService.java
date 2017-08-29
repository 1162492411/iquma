package com.iquma.service;

import com.iquma.pojo.Reply;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mo on 2016/10/4.
 */
public interface ReplyService {

    Boolean insert(Reply record);//发表回复

    Boolean deleteById(Integer id);//删除指定id的回复

    Boolean update(Reply record);//更新回复

    Boolean changeStatus(Integer id);//关闭回复

    Boolean rate(Reply record);//更新回复评分

    Boolean adopt(Integer id);//采纳回复

    Reply selectById(Integer id);//查找指定id的回复

    int selectsCount(Reply condition);//查找主贴的所有回复的总数

    List selectsByPage(int page, Reply condition);//分页查找符合条件的回复

    List selectsByPageSorted(int page,Reply condition);//查找主贴的所有回复并按时间排序

}
