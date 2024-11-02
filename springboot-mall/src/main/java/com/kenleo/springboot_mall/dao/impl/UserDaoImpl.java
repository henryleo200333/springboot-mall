package com.kenleo.springboot_mall.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.kenleo.springboot_mall.dao.UserDao;
import com.kenleo.springboot_mall.dto.UserRegisterRequest;
import com.kenleo.springboot_mall.model.User;
import com.kenleo.springboot_mall.rowmapper.UserRowMapper;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Integer createUser(UserRegisterRequest userRegisterRequest) {
		String sql = "INSERT INTO user(email, password, created_date, last_modified_date) "
				+ "VALUES(:email, :password, :created_date, :last_modified_date)";

		Map<String, Object> map = new HashMap<>();
		map.put("email", userRegisterRequest.getEmail());
		map.put("password", userRegisterRequest.getPassword());

		Date now = new Date();
		map.put("created_date", now);
		map.put("last_modified_date", now);

		KeyHolder keyholder = new GeneratedKeyHolder();

		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyholder);

		int userId = keyholder.getKey().intValue();

		return userId;
	}

	@Override
	public User getUserById(Integer userId) {

		String sql = "select * from user where user_id = :userId";

		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);

		List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

		if (userList.isEmpty()) {
			return null;
		}

		return userList.get(0);
	}

	@Override
	public User getUserByEmail(String email) {

		String sql = "select * from user where email = :email";

		Map<String, Object> map = new HashMap<>();
		map.put("email", email);

		List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

		if (userList.isEmpty()) {
			return null;
		}

		return userList.get(0);

	}


}
