package com.example.figureshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.figureshop.entity.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Long>{
	List<Shipping> findByUserId(Long userId);
}
