package com.iquma.service;

import java.util.List;

/**
 * Created by Mo on 2016/11/29.
 */
public interface RolePerService {

    List selectPersByRid(Byte rid);//获取角色拥有的权限

}
