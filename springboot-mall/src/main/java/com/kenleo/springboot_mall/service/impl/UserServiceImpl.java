package com.kenleo.springboot_mall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import com.kenleo.springboot_mall.dao.UserDao;
import com.kenleo.springboot_mall.dto.UserLoginRequest;
import com.kenleo.springboot_mall.dto.UserRegisterRequest;
import com.kenleo.springboot_mall.model.User;
import com.kenleo.springboot_mall.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDao userDao;

	@Override
	public Integer register(UserRegisterRequest userRegisterRequest) {

		User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

		if (user != null) {
			log.warn("該 email {} 已經被註冊", userRegisterRequest.getEmail());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		//使用MD5 生成密碼雜湊
		String hashedPwd = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes()); 
		userRegisterRequest.setPassword(hashedPwd);
		
		return userDao.createUser(userRegisterRequest);
	}

	@Override
	public User getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}

	@Override
	public User login(UserLoginRequest userLoginRequest) {
		User user = userDao.getUserByEmail(userLoginRequest.getEmail());
		if(user == null) {
			log.warn("該 email {} 尚未註冊", userLoginRequest.getEmail());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		String hashedPwd = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes()); 
		if(!user.getPassword().equals(hashedPwd)) {
			log.warn("email {} 密碼不正確", userLoginRequest.getEmail());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return user;
	}

}
