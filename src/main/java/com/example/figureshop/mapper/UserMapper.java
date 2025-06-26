package com.example.figureshop.mapper;

import com.example.figureshop.dto.UserRegisterRequestDTO;
import com.example.figureshop.dto.UserResponseDTO;
import com.example.figureshop.entity.User;

public class UserMapper {

	public static User toEntity(UserRegisterRequestDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddress(dto.getAddress());
        user.setRole(dto.getRole());
        user.setVerified(dto.getVerified());
        return user;
    }
	
	public static UserResponseDTO toDto(User user) {
		UserResponseDTO userDto = new UserResponseDTO();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		return userDto;
	}
}
