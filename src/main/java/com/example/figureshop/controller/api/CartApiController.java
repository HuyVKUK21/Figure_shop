package com.example.figureshop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.figureshop.dto.response.CartDtoResponse;
import com.example.figureshop.response.ApiResponse;
import com.example.figureshop.service.ICartService;

@RestController
@RequestMapping("/api")
public class CartApiController {
	
	@Autowired
	private ICartService cartService;
	
	@GetMapping("/cart")
	public ResponseEntity<ApiResponse<List<CartDtoResponse>>> getCart(@RequestParam("userId") Long userId) {
		List<CartDtoResponse> cartDtoResponses = cartService.getCart(userId);
		ApiResponse<List<CartDtoResponse>> response = ApiResponse.success(cartDtoResponses);
		return ResponseEntity.ok(response);
	}
	
}
