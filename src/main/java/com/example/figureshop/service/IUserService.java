package com.example.figureshop.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.figureshop.dto.request.RegisterDtoRequest;
import com.example.figureshop.entity.User;

public interface IUserService {
	UserDetails loadUserById(Long userId);
	void createUser(RegisterDtoRequest dto);
}
