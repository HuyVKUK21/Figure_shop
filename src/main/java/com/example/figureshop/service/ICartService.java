package com.example.figureshop.service;

import java.util.List;

import com.example.figureshop.dto.request.AddCartDtoRequest;
import com.example.figureshop.dto.request.UpdateCartDtoRequest;
import com.example.figureshop.dto.response.CartDtoResponse;
import com.example.figureshop.entity.Cart;

public interface ICartService {
	List<CartDtoResponse> getCart(Long userId);
	CartDtoResponse addCart(AddCartDtoRequest addCartDtoRequest);
	CartDtoResponse updateCart(UpdateCartDtoRequest cartDtoRequest);
	void deleteCartItem(Long cartId);
	void deleteCartByUserId(Long userId);
}
