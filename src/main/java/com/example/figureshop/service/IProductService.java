package com.example.figureshop.service;

import java.util.List;

import com.example.figureshop.dto.response.ProductDtoResponse;

public interface IProductService {
	List<ProductDtoResponse> getAllProducts();
}
