package com.easyframework.admin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyframework.base.BaseService;
import com.easyframework.model.User;
import com.easyframework.mybatis.UserMapper;


@Service
@Transactional
public class UserService extends BaseService<User> {
    	
    @Resource
    private UserMapper userMapper;

}
