package com.example.figureshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.figureshop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
