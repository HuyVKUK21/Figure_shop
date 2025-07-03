package com.example.figureshop.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.figureshop.dto.response.ProductDtoResponse;
import com.example.figureshop.dto.response.UserDtoResponse;
import com.example.figureshop.response.ApiResponse;
import com.example.figureshop.security.CustomUserDetails;

@RestController
@RequestMapping("/api")
public class UserApiController {
	@GetMapping("/userProfile")
	public ResponseEntity<ApiResponse<UserDtoResponse>> getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
		UserDtoResponse user = new UserDtoResponse();
		user.setUserId(userDetails.getUser().getUserId());
		user.setUserEmail(userDetails.getUser().getUserEmail());
		user.setUserName(userDetails.getUser().getUserName());
		user.setUserPhone(userDetails.getUser().getUserPhone());
		user.setUserAddress(userDetails.getUser().getUserAddress());
		user.setRole(userDetails.getUser().getRole());
		user.setCreatedAt(userDetails.getUser().getCreatedAt());
		user.setUpdatedAt(userDetails.getUser().getUpdatedAt());
		ApiResponse<UserDtoResponse> response = ApiResponse.success(user);
		return ResponseEntity.ok(response);
	}
 }
