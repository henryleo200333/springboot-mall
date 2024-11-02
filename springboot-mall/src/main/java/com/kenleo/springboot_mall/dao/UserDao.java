package com.kenleo.springboot_mall.dao;

import com.kenleo.springboot_mall.dto.UserRegisterRequest;
import com.kenleo.springboot_mall.model.User;

public interface UserDao {
	Integer createUser(UserRegisterRequest userRegisterRequest);

	User getUserById(Integer userId);
}
