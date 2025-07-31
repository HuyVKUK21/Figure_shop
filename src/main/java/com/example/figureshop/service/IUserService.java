package com.example.figureshop.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.figureshop.dto.request.UserDtoRequest;
import com.example.figureshop.dto.response.UserDtoResponse;
import com.example.figureshop.entity.User;

public interface IUserService {
	UserDetails loadUserById(Long userId);
	UserDtoResponse createUser(UserDtoRequest dto);
	Long checkUserExist(String email);
}
