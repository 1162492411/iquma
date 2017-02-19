package com.iquma.dao;

import com.iquma.pojo.Favorite;

import java.util.List;

public interface FavoriteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Favorite record);

    int insertSelective(Favorite record);

    Favorite selectByCondition(Favorite condition);//筛选符合条件的收藏信息

    int updateByPrimaryKeySelective(Favorite record);

    int updateByPrimaryKey(Favorite record);

    List selectsByCondition(Favorite condition);//筛选符合条件的收藏信息集合

}