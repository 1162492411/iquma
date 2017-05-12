package com.iquma.dao;

import com.iquma.pojo.Reply;


import java.util.List;

public interface ReplyMapper {

    int insertSelective(Reply record);//插入回复

    int deleteByPrimaryKey(Integer id);//删除回复

    int updateByPrimaryKeySelective(Reply record);//更新回复

    boolean changeStatusByPrimaryKey(Integer id);//改变回复状态

    boolean adopt(Integer id);//采纳回复

    Reply selectByPrimaryKey(Integer id);//根据id查找回复

    List selectByCondition(Reply record);//查找符合条件的所有回复

    List selectByConditionSortByTime(Reply record);//查找符合条件的所有回复并按时间排序

}