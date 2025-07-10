package com.example.figureshop.mapper;

import com.example.figureshop.dto.response.CartDtoResponse;
import com.example.figureshop.dto.response.ProductDtoResponse;
import com.example.figureshop.entity.Cart;
import com.example.figureshop.entity.Product;

public class CartMapper {
	public static CartDtoResponse toDto(Cart cart) {
		CartDtoResponse cartDtoResponse = new CartDtoResponse();
		cartDtoResponse.setCartId(cart.getCartId());
		cartDtoResponse.setCategoryId(cart.getCategoryProduct().getCategoryId());
		cartDtoResponse.setProductId(cart.getProduct().getProductId());
		cartDtoResponse.setUserId(cart.getUser().getUserId());
		cartDtoResponse.setQuantity(cart.getQuantity());
		cartDtoResponse.setProductImage(cart.getProduct().getProductImages());
		cartDtoResponse.setProductName(cart.getProduct().getProductName());
		cartDtoResponse.setProductPrice(cart.getProduct().getProductPrice());
		
		return cartDtoResponse;
	}
}
