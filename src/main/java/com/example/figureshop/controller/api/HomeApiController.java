package com.example.figureshop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.figureshop.dto.ProductDto;
import com.example.figureshop.response.ApiResponse;
import com.example.figureshop.service.IProductService;

@RestController
@RequestMapping("api/product")
public class HomeApiController {

	@Autowired
	private IProductService productService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<ProductDto>>> getAll() {
		List<ProductDto> product = productService.getAllProducts();
		ApiResponse<List<ProductDto>> response = ApiResponse.success(product);
		return ResponseEntity.ok(response);
	}
}
