package com.iquma.dao;

import com.iquma.pojo.Comment;

import java.util.ArrayList;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    Comment selectCommentByCondition(Comment comment);//根据条件查找评论 -- 未实现

    ArrayList selectCommentsByCondition(Comment comment);//根据条件查找评论集合 -- 未实现

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);
}