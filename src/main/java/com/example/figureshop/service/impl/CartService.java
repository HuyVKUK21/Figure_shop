package com.example.figureshop.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.figureshop.dto.response.CartDtoResponse;
import com.example.figureshop.entity.Cart;
import com.example.figureshop.mapper.CartMapper;
import com.example.figureshop.repository.CartRepository;
import com.example.figureshop.service.ICartService;

@Service
public class CartService implements ICartService{
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public List<CartDtoResponse> getCart(Long userId) {
		List<Cart> cartDtoResponses = cartRepository.findByUser_UserId(userId);
		return cartDtoResponses.stream().map(CartMapper::toDto).collect(Collectors.toList());
	}

}
