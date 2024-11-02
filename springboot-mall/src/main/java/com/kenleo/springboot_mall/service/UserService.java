package com.kenleo.springboot_mall.service;

import com.kenleo.springboot_mall.dto.UserRegisterRequest;
import com.kenleo.springboot_mall.model.User;

public interface UserService {

	Integer register(UserRegisterRequest userRegisterRequest);

	User getUserById(Integer userId);

}
