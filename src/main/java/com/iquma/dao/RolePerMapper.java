package com.iquma.dao;

import com.iquma.pojo.RolePerKey;

public interface RolePerMapper {
    int deleteByPrimaryKey(RolePerKey key);

    int insert(RolePerKey record);

    int insertSelective(RolePerKey record);
}