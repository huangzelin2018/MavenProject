package com.easyframework.admin.service;

import java.util.HashMap;
import java.util.Map;

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

	public Map<String, Object> getUserList(Map<String, Object> paramsMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", 100);
		map.put("data", userMapper.queryUserByMap(paramsMap));
		return map;
	}

}
