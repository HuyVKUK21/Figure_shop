package com.example.figureshop.service;

import java.util.List;

import com.example.figureshop.dto.UserRegisterRequestDTO;
import com.example.figureshop.dto.UserResponseDTO;

public interface IUserService {
    void createUser(UserRegisterRequestDTO dto);
    List<UserResponseDTO> getAllUsers();
}