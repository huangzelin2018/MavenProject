package com.easyframework.mybatis;

import com.easyframework.base.Mapper;
import com.easyframework.model.User;

public interface UserMapper extends Mapper<User> {

	public User findUserByUserName(String username);
}