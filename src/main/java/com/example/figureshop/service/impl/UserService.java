package com.example.figureshop.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.figureshop.entity.User;
import com.example.figureshop.repository.UserRepository;
import com.example.figureshop.security.CustomUserDetails;
import com.example.figureshop.service.IUserService;

@Service
public class UserService implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomUserDetails(user);
	}

	@Override
	public UserDetails loadUserById(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User ID not found: " + userId));
		return new CustomUserDetails(user);
	}

	

}
