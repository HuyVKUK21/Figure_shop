package com.example.figureshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.figureshop.dto.response.OrderDtoResponse;
import com.example.figureshop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	@Modifying
	@Query("UPDATE Order c SET c.orderStatus = :status WHERE c.orderId = :orderId")
	void updateOrderStatus(@Param("orderId") Long orderId, @Param("status") String status);

	Optional<Order> findTopByUserIdAndOrderStatusOrderByCreatedAtDesc(Long userId, String orderStatus);
	List<Order> findByUserIdAndOrderStatus(Long userId, String orderStatus);

}
