package com.iquma.service;

import com.iquma.pojo.Suclog;

import java.util.List;

/**
 * Created by Mo on 2017/2/16.
 */
public interface SuclogService {

    Boolean insert(Suclog record);//记录日志

    List selectsByUid(String uid);//返回用户所有登录日志

    Boolean deletesByUid(String uid);//清空用户所有登录日志

}
