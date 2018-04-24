package com.easyframework.mybatis;

import java.util.List;
import java.util.Map;

import com.easyframework.base.Mapper;
import com.easyframework.model.User;

public interface UserMapper extends Mapper<User> {

//	@Select("SELECT * FROM USER")
	List<User> queryUserByMap(Map<String, Object> paramsMap);

	int queryUserCountByMap(Map<String, Object> paramsMap);
}