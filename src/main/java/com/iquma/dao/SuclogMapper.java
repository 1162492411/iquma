package com.iquma.dao;

import com.iquma.pojo.Suclog;

import java.util.List;

public interface SuclogMapper {

    int deleteByUid(String  uid);

    int insert(Suclog record);

    List selectsByUid(String uid);

}