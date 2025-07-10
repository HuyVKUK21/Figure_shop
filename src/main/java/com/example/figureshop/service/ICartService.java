package com.example.figureshop.service;

import java.util.List;

import com.example.figureshop.dto.response.CartDtoResponse;

public interface ICartService {
	List<CartDtoResponse> getCart(Long userId);
}
