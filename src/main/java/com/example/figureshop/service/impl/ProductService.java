package com.example.figureshop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import com.example.figureshop.dto.response.ProductDtoResponse;
import com.example.figureshop.entity.Product;
import com.example.figureshop.exception.NotFoundException;
import com.example.figureshop.mapper.ProductMapper;
import com.example.figureshop.repository.ProductRepository;
import com.example.figureshop.service.IProductService;

@Service
public class ProductService implements IProductService{
	@Autowired
	private ProductRepository productRepository;


	@Override
	public List<ProductDtoResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(ProductMapper::toDto).collect(Collectors.toList());
	}


	@Override
	public ProductDtoResponse getDetailsProduct(Long productId) {
		  Product product = productRepository.findById(productId)
		            .orElseThrow(() -> new NotFoundException("Product not found"));
		return ProductMapper.toDto(product);
	}
	

}
