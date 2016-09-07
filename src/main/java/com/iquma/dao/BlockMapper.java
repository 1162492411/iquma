package com.iquma.dao;

import com.iquma.pojo.Block;

public interface BlockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Block record);

    int insertSelective(Block record);

    Block selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Block record);

    int updateByPrimaryKey(Block record);
}