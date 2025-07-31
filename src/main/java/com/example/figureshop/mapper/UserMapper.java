package com.example.figureshop.mapper;

import com.example.figureshop.dto.request.UserDtoRequest;
import com.example.figureshop.dto.response.UserDtoResponse;
import com.example.figureshop.entity.User;

public class UserMapper {

	public static User toEntity(UserDtoRequest dto) {
		User user = new User();
		user.setUserEmail(dto.getUserEmail());
		user.setUserPassword(dto.getUserPassword());
		user.setUserName(dto.getUserName());
		user.setUserPhone(dto.getUserPhone());
		user.setUserProvider(dto.getUserProvider());
		return user;
	}
	
	public static UserDtoResponse toResponse(User user) {
		UserDtoResponse userDtoResponse = new UserDtoResponse();
		userDtoResponse.setUserId(user.getUserId());
		userDtoResponse.setUserEmail(user.getUserEmail());
		userDtoResponse.setUserName(user.getUserName());
		userDtoResponse.setUserPhone(user.getUserPhone());
		userDtoResponse.setUserAddress(user.getUserAddress());
		userDtoResponse.setRole(user.getRole());
		userDtoResponse.setCreatedAt(user.getCreatedAt());
		userDtoResponse.setUpdatedAt(user.getUpdatedAt());
		return userDtoResponse;
	}
}
