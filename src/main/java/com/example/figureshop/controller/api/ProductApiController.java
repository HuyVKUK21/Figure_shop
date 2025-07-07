package com.example.figureshop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.figureshop.dto.response.ProductDtoResponse;
import com.example.figureshop.response.ApiResponse;
import com.example.figureshop.service.IProductService;

@RestController
@RequestMapping("/api/product")
public class ProductApiController {
	@Autowired
	private IProductService productService;
	
	@GetMapping("/detail-product")
	public ResponseEntity<ApiResponse<ProductDtoResponse>> getDetailsProduct(@RequestParam("productId") Long productId) {	
		ProductDtoResponse product = productService.getDetailsProduct(productId);
		ApiResponse<ProductDtoResponse> response = ApiResponse.success(product);
		return ResponseEntity.ok(response);		
	}
}
