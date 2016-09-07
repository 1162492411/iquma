package com.iquma.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.iquma.dao.UserMapper;
import com.iquma.pojo.User;
import com.iquma.service.UserService;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserMapper userMapper;

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
	public boolean validatorUserPass(String id, String pass) {
		return this.getUserById(id).getPass().equals(pass);
	}

	@Override
	public boolean isAdmin(String id) {
		return this.getUserById(id).getRid() >= 1 && this.getUserById(id).getRid() <= 3;
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
