package com.example.figureshop.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
	UserDetails loadUserById(Long userId);
}
