package com.iquma.dao;

import com.iquma.pojo.Suclog;

public interface SuclogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Suclog record);

    int insertSelective(Suclog record);

    Suclog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Suclog record);

    int updateByPrimaryKey(Suclog record);
}