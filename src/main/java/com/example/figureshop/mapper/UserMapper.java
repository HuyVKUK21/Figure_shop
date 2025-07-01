package com.example.figureshop.mapper;

import com.example.figureshop.dto.request.RegisterDtoRequest;
import com.example.figureshop.dto.response.UserDtoResponse;
import com.example.figureshop.entity.User;

public class UserMapper {

	public static User toEntity(RegisterDtoRequest dto) {
		User user = new User();
		user.setUserEmail(dto.getUserEmail());
		user.setUserPassword(dto.getUserPassword());
		user.setUserName(dto.getUserName());
		user.setUserPhone(dto.getUserPhone());
		return user;
	}
}
