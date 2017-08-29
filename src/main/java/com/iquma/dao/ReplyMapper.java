package com.iquma.dao;

import com.iquma.pojo.Reply;
import com.iquma.pojo.Topic;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface ReplyMapper {

    int insertSelective(Reply record);//插入回复

    int deleteByPrimaryKey(Integer id);//删除回复

    int updateByPrimaryKeySelective(Reply record);//更新回复

    boolean changeStatusByPrimaryKey(Integer id);//改变回复状态

    boolean adopt(Integer id);//采纳回复

    Reply selectByPrimaryKey(Integer id);//根据id查找回复

    int selectsCount(Reply record);//查找符合条件的所有回复的总数

    List selectsByPage(@Param("start")int start,@Param("rep")Reply record);//按分页查找符合条件的所有回复

    List selectsByPageSorted(@Param("start")int start,@Param("rep")Reply record);//查找符合条件的所有回复并按时间排序

}