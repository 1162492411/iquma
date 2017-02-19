package com.iquma.dao;

import com.iquma.pojo.Reply;


import java.util.List;

public interface ReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKeyWithBLOBs(Reply record);

    int updateByPrimaryKey(Reply record);

    boolean changeStatusByPrimaryKey(Integer id);//改变回复状态--建议替换blockByPrimaryKey

    boolean adopt(Integer id);//采纳回复

    List selectByCondition(Reply record);//查找符合条件的所有回复

    List selectByConditionSortByTime(Reply record);//查找符合条件的所有回复并按时间排序

}