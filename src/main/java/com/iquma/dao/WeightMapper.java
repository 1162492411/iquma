package com.iquma.dao;

import com.iquma.pojo.Weight;
import com.iquma.pojo.WeightKey;

public interface WeightMapper {
    int deleteByPrimaryKey(WeightKey key);

    int insert(Weight record);

    int insertSelective(Weight record);

    Weight selectByPrimaryKey(WeightKey key);

    int updateByPrimaryKeySelective(Weight record);

    int updateByPrimaryKey(Weight record);
}