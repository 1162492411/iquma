package com.iquma.dao;

import com.iquma.pojo.Reply;

import java.util.ArrayList;

public interface ReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Integer id);

    Reply selectReplyByCondition(Reply reply);//根据条件查找单个回复 --未实现

    ArrayList selectReplysByCondition(Reply reply);//根据条件查找回复集合 -- 未实现

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKeyWithBLOBs(Reply record);

    int updateByPrimaryKey(Reply record);
}