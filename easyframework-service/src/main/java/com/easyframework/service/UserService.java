package com.easyframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyframework.base.BaseService;
import com.easyframework.model.User;
import com.easyframework.mybatis.UserMapper;

@Service
@Transactional
public class UserService extends BaseService<User> {

	@Autowired
	private UserMapper userMapper;

	public User findUserByUserName(String username) {
		return userMapper.findUserByUserName(username);
	}

}
