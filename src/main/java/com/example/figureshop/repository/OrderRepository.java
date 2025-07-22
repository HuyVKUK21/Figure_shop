package com.example.figureshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.figureshop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
