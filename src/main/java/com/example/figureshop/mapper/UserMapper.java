package com.example.figureshop.mapper;

import com.example.figureshop.dto.response.UserDtoResponse;
import com.example.figureshop.entity.User;

public class UserMapper {

	public static User toEntity(UserDtoResponse dto) {
		User user = new User();
		user.setUserId(dto.getUserId());
		user.setUserEmail(dto.getUserEmail());
		user.setUserPassword(dto.getUserPassword());
		user.setUserName(dto.getUserName());
		user.setUserPhone(dto.getUserPhone());
		user.setUserAddress(dto.getUserAddress());
		user.setRole(dto.getRole());
		return user;
	}
}
