package com.example.figureshop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.figureshop.dto.response.ProductDtoResponse;
import com.example.figureshop.dto.response.ShippingDtoResponse;
import com.example.figureshop.response.ApiResponse;
import com.example.figureshop.service.IShippingService;
import com.example.figureshop.util.AuthUtils;

@RestController
@RequestMapping("/api")

public class ShippingApiController {
	@Autowired
	private IShippingService shippingService;
	
	@GetMapping("/shipping")
	public ResponseEntity<ApiResponse<List<ShippingDtoResponse>>> getInfoShipping() {	
		Long userId = AuthUtils.getCurrentUserId();
		List<ShippingDtoResponse> shippingDtoResponse = shippingService.getUserShipping(userId);
		ApiResponse<List<ShippingDtoResponse>> response = ApiResponse.success(shippingDtoResponse);
		return ResponseEntity.ok(response);		
	}
}
