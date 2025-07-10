package com.example.figureshop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.figureshop.dto.response.CartDtoResponse;
import com.example.figureshop.response.ApiResponse;
import com.example.figureshop.security.CustomUserDetails;
import com.example.figureshop.service.ICartService;

@RestController
@RequestMapping("/api")
public class CartApiController {

	@Autowired
	private ICartService cartService;

	@GetMapping("/cart")
	public ResponseEntity<ApiResponse<List<CartDtoResponse>>> getCart() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		Long userId = user.getUser().getUserId();
		List<CartDtoResponse> cartDtoResponses = cartService.getCart(userId);
		ApiResponse<List<CartDtoResponse>> response = ApiResponse.success(cartDtoResponses);
		return ResponseEntity.ok(response);
	}

}
