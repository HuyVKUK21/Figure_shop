package com.example.figureshop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.figureshop.dto.response.ProductDtoResponse;
import com.example.figureshop.response.ApiResponse;
import com.example.figureshop.service.IProductService;

@RestController
@RequestMapping("/api")
public class HomeApiController {

	@Autowired
	private IProductService productService;
	
	@GetMapping("/productAll")
	public ResponseEntity<ApiResponse<List<ProductDtoResponse>>> getAll() {
		List<ProductDtoResponse> product = productService.getAllProducts();
		ApiResponse<List<ProductDtoResponse>> response = ApiResponse.success(product);
		return ResponseEntity.ok(response);
	}
}
