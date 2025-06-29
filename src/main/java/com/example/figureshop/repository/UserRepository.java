package com.example.figureshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.figureshop.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserName(String userName);
}
