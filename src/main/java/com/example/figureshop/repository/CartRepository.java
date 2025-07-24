package com.example.figureshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.figureshop.dto.response.CartDtoResponse;
import com.example.figureshop.entity.Cart;
import com.example.figureshop.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long>{
	List<Cart> findByUser_UserId(Long userId);
	
	void deleteByUser_UserId(Long userId);

	@Modifying
	@Query("UPDATE Cart c SET c.quantity = :quantity WHERE c.cartId = :cartId")
	int updateQuantityCart(@Param("cartId") Long cartId, @Param("quantity") int qty);
}
