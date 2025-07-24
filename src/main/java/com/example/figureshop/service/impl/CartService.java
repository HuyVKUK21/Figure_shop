package com.example.figureshop.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.figureshop.dto.request.AddCartDtoRequest;
import com.example.figureshop.dto.request.UpdateCartDtoRequest;
import com.example.figureshop.dto.response.CartDtoResponse;
import com.example.figureshop.entity.Cart;
import com.example.figureshop.exception.NotFoundException;
import com.example.figureshop.mapper.CartMapper;
import com.example.figureshop.mapper.ProductMapper;
import com.example.figureshop.repository.CartRepository;
import com.example.figureshop.service.ICartService;

import jakarta.transaction.Transactional;

@Service
public class CartService implements ICartService {

	@Autowired
	private CartRepository cartRepository;
	private ProductMapper productMapper;

	@Override
	public List<CartDtoResponse> getCart(Long userId) {
		List<Cart> cart = cartRepository.findByUser_UserId(userId);
		return cart.stream().map(CartMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public CartDtoResponse addCart(AddCartDtoRequest addCartDtoRequest) {
		Cart cart = CartMapper.addCartRequestDto(addCartDtoRequest);
		cartRepository.save(cart);
		return CartMapper.toDto(cart);
	}

	@Override
	@Transactional
	public CartDtoResponse updateCart(UpdateCartDtoRequest cartDtoRequest) {
		cartRepository.updateQuantityCart(cartDtoRequest.getCartId(), cartDtoRequest.getQuantity());
		Cart updatedCart = cartRepository.findById(cartDtoRequest.getCartId())
				.orElseThrow(() -> new RuntimeException("Cart not found"));

		return CartMapper.toDto(updatedCart);
	}

	@Override
	@Transactional
	public void deleteCartItem(Long cartId) {
		if (!cartRepository.existsById(cartId)) {
			throw new NotFoundException("Cart with id " + cartId + " not found.");
		}
		cartRepository.deleteById(cartId);

	}

}
