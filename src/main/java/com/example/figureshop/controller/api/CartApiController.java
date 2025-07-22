package com.example.figureshop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.figureshop.dto.request.AddCartDtoRequest;
import com.example.figureshop.dto.request.UpdateCartDtoRequest;
import com.example.figureshop.dto.response.CartDtoResponse;
import com.example.figureshop.entity.Cart;
import com.example.figureshop.response.ApiResponse;
import com.example.figureshop.security.CustomUserDetails;
import com.example.figureshop.service.ICartService;
import com.example.figureshop.util.AuthUtils;

@RestController
@RequestMapping("/api")
public class CartApiController {

	@Autowired
	private ICartService cartService;

	
	@GetMapping("/cart")
	public ResponseEntity<ApiResponse<List<CartDtoResponse>>> getCart() {
		Long userId = AuthUtils.getCurrentUserId();
		List<CartDtoResponse> cartDtoResponses = cartService.getCart(userId);
		ApiResponse<List<CartDtoResponse>> response = ApiResponse.success(cartDtoResponses);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/addcart")
	public ResponseEntity<ApiResponse<CartDtoResponse>> addCart(@RequestBody AddCartDtoRequest addCartDtoRequest) {
		Long userId = AuthUtils.getCurrentUserId();
		addCartDtoRequest.setUserId(userId);
		CartDtoResponse cartDtoResponse = cartService.addCart(addCartDtoRequest);
		ApiResponse<CartDtoResponse> response = ApiResponse.created("Success", cartDtoResponse);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/updatecart")
	public ResponseEntity<ApiResponse<CartDtoResponse>> updateCart(@RequestBody UpdateCartDtoRequest cartDtoRequest) {
		CartDtoResponse cartDtoResponse = cartService.updateCart(cartDtoRequest);
		ApiResponse<CartDtoResponse> response = ApiResponse.success(cartDtoResponse);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("delete-item-cart")
	public ResponseEntity<ApiResponse<String>> deleteItemCart(@RequestParam Long cartId) {
		cartService.deleteCartItem(cartId);
		ApiResponse<String> response = ApiResponse.success(null);
		return ResponseEntity.ok(response);
	}
}
