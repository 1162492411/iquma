package com.iquma.dao;

import com.iquma.pojo.RolePerKey;

import java.util.List;

public interface RolePerMapper {
    int deleteByPrimaryKey(RolePerKey key);

    int insert(RolePerKey record);

    int insertSelective(RolePerKey record);

    List selectPersById(Byte rid);
}