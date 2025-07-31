package com.example.figureshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.figureshop.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserName(String userName);
	Optional<User> findByUserEmail(String userEmail);
}
