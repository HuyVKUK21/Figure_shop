package com.example.figureshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.figureshop.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

}
