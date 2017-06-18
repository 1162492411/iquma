package com.iquma.service;

import com.iquma.pojo.Favorite;

import java.util.List;

/**
 * Created by Mo on 2016/12/7.
 */
public interface FavoriteService {

    boolean insert(Favorite record);//插入收藏记录

    boolean delete(Integer id);//删除收藏记录

    List selectsByConditionAndPage(int page,Favorite condition);//筛选符合条件的收藏信息

    Favorite selectByCondition(Favorite condition);//查询符合条件的收藏信息

    int selectCountByCondition(Favorite condition);//获取符合条件的收藏信息的总数

}
