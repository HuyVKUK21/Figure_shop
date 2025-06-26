package com.example.figureshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.figureshop.dto.UserRegisterRequestDTO;
import com.example.figureshop.dto.UserResponseDTO;
import com.example.figureshop.entity.User;
import com.example.figureshop.mapper.UserMapper;
import com.example.figureshop.repository.IUserRepository;
import com.example.figureshop.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void createUser(UserRegisterRequestDTO dto) {
		User user = UserMapper.toEntity(dto);
		String passwordHashed = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(passwordHashed);
        userRepository.save(user);
	}

	@Override
	public List<UserResponseDTO> getAllUsers() {
		 return userRepository.findAll()
	                .stream()
	                .map(UserMapper::toDto)
	                .collect(Collectors.toList());
	    
	}
}
