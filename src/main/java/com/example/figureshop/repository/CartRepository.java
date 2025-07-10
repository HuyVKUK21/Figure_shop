package com.example.figureshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.figureshop.entity.Cart;
import com.example.figureshop.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long>{
	List<Cart> findByUser_UserId(Long userId);

}
