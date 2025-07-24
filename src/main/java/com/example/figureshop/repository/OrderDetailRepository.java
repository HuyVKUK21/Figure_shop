package com.example.figureshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.figureshop.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
	List<OrderDetail> findByOrder_OrderId(Long orderId);
}
