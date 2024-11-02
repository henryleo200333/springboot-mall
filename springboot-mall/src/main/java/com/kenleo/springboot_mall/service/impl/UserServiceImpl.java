package com.kenleo.springboot_mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kenleo.springboot_mall.dao.UserDao;
import com.kenleo.springboot_mall.dto.UserRegisterRequest;
import com.kenleo.springboot_mall.model.User;
import com.kenleo.springboot_mall.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public Integer register(UserRegisterRequest userRegisterRequest) {
		return userDao.createUser(userRegisterRequest);
	}

	@Override
	public User getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}

}
