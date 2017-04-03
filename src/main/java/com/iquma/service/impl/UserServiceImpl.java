package com.iquma.service.impl;

import java.util.List;
import javax.annotation.Resource;

import com.iquma.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iquma.dao.UserMapper;
import com.iquma.pojo.User;
import com.iquma.service.UserService;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;
    @Autowired
    private PasswordHelper passwordHelper;
    private static int SUPER_ADMIN_ID = 1;//最高权限的超级管理员的角色Id
    private static int SUPER_STUDENT_ID = 3;//最高级别的学生的角色Id

    //@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, readOnly = true, timeout = 3)
    public User selectById(String id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List selectAll() {
        return this.userMapper.selectAll();
    }


    public boolean insert(User user) {

        passwordHelper.encryptPassword(user);
        return this.userMapper.insert(user) > 0;
    }

    @Override
    public boolean delete(String id) {
        return this.userMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean updatePrestige(String id, Integer prestige) {
        System.out.println("Service中更新用户" + id +"的威望,即将" + prestige);
        return this.userMapper.updatePrestige(id,prestige) > 0;
    }

    @Override
    public boolean updateRid(String id, Byte rid) {
        return this.userMapper.updateRid(id,rid) > 0;
    }

    @Override
    public boolean validatorUserPass(String id, String pass) {
        return this.selectById(id).getPass().equals(pass);
    }

    @Override
    public boolean validatorIsAdmin(String id) {
        return this.selectById(id).getRid() >= SUPER_ADMIN_ID && this.selectById(id).getRid() <= SUPER_STUDENT_ID;
    }

    @Override
    public boolean validatorEmail(String id, String email) {
        return this.selectById(id).getEmail().equals(email);
    }

    @Override
    public boolean validatorStatus(String id) {
        return this.selectById(id).getIsBlock();
    }

    @Override
    public boolean changeStatus(String id) {
        return this.userMapper.changeStatusByPrimaryKey(id);
    }

    //更新用户信息
    public boolean update(User record) {
        return this.userMapper.updateByPrimaryKeySelective(record) > 0;
    }


}
