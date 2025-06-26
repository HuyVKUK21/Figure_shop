package com.example.figureshop.repository;

import java.util.List;

import com.example.figureshop.entity.User;

public interface IUserRepository {
    void save(User user);
    List<User> findAll();
}