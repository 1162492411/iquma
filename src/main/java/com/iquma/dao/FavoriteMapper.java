package com.iquma.dao;

import com.iquma.pojo.Favorite;

import java.util.List;

public interface FavoriteMapper {

    int insertSelective(Favorite record);//添加收藏记录

    int deleteByPrimaryKey(Integer id);//删除收藏记录

    Favorite selectByCondition(Favorite condition);//筛选符合条件的收藏信息

    List selectsByCondition(Favorite condition);//筛选符合条件的收藏信息集合

}