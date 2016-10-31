package com.iquma.service.impl;

import java.util.ArrayList;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iquma.dao.UserMapper;
import com.iquma.pojo.User;
import com.iquma.service.UserService;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;
    private static int SUPER_ADMIN_ID = 1;//最高权限的超级管理员的角色Id
    private static int SUPER_STUDENT_ID = 3;//最高级别的学生的角色Id

    //@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = true, timeout = 3)
    public User getUserById(String id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public ArrayList getAllUsers() {
        return this.userMapper.getAllUsers();
    }


    public boolean insert(User user) {
        return this.userMapper.insert(user) > 0;
    }

    @Override
    public boolean delete(String id) {
        return this.userMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean validatorUserPass(String id, String pass) {
        return this.getUserById(id).getPass().equals(pass);
    }

    @Override
    public boolean isAdmin(String id) {
        return this.getUserById(id).getRid() >= SUPER_ADMIN_ID && this.getUserById(id).getRid() <= SUPER_STUDENT_ID;
    }

    @Override
    public boolean validatorEmail(String id, String email) {
        return this.getUserById(id).getEmail().equals(email);
    }

    @Override
    public boolean validatorUserStatus(String id) {
        return this.getUserById(id).getIsBlock();
    }

    @Override
    public boolean blockUser(String id) {
        return this.userMapper.blockByPrimaryKey(id);

    }

    //更新用户信息
    public boolean updateUser(User record) {
        return this.userMapper.updateByPrimaryKeySelective(record) > 0;
    }


}
