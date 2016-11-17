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

   // List selectByTid(Integer tid);//查找某主贴下的所有回复--建议废弃

    List selectByCondition(Reply record);//查找符合条件的所有回复

}