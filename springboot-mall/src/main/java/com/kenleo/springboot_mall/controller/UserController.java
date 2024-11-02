package com.kenleo.springboot_mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kenleo.springboot_mall.dto.UserLoginRequest;
import com.kenleo.springboot_mall.dto.UserRegisterRequest;
import com.kenleo.springboot_mall.model.User;
import com.kenleo.springboot_mall.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/users/register")
	public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
		
		Integer userId = userService.register(userRegisterRequest);
		User user = userService.getUserById(userId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@PostMapping("/users/login")
	public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userRegisterRequest){
		
		User user = userService.login(userRegisterRequest);
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
		
	}
	
	
}
