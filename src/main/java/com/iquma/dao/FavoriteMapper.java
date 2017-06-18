package com.iquma.dao;

import com.iquma.pojo.Favorite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoriteMapper {

    int insertSelective(Favorite record);//添加收藏记录

    int deleteByPrimaryKey(Integer id);//删除收藏记录

    Favorite selectByCondition(Favorite condition);//筛选符合条件的收藏信息

    List selectsByConditionAndPage(@Param("start")int start,@Param("end")int end,@Param("fav")Favorite condition);//筛选符合条件的收藏信息集合

    int selectCountByCondition(Favorite condition);//获取符合条件的收藏信息的总数

}