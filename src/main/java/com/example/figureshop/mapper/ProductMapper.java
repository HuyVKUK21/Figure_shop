package com.example.figureshop.mapper;

import com.example.figureshop.dto.response.ProductDtoResponse;
import com.example.figureshop.entity.Product;

public class ProductMapper {
	
	public static Product toEntity(ProductDtoResponse dto) {
		Product product = new Product();
		product.setProductId(dto.getProductId());
		product.setCategoryId(dto.getCategoryId());
		product.setBrandId(dto.getBrandId());
		product.setProductName(dto.getProductName());
		product.setProductDesc(dto.getProductDesc());
		product.setProductSeries(dto.getProductSeries());
		product.setProductSize(dto.getProductSize());
		product.setProductProportion(dto.getProductProportion());
		product.setProductDate(dto.getProductDate());
		product.setProductPrice(dto.getProductPrice());
		product.setProductPriceLog(dto.getProductPriceLog());
		product.setProductStatus(dto.getProductStatus());	
		return product;
	}
	
	public static ProductDtoResponse toDto(Product product) {
		ProductDtoResponse productDto = new ProductDtoResponse();
		productDto.setProductId(product.getProductId());
		productDto.setCategoryId(product.getCategoryId());
		productDto.setBrandId(product.getBrandId());
		productDto.setProductName(product.getProductName());
		productDto.setProductDesc(product.getProductDesc());
		productDto.setProductSeries(product.getProductSeries());
		productDto.setProductSize(product.getProductSize());
		productDto.setProductProportion(product.getProductProportion());
		productDto.setProductDate(product.getProductDate());
		productDto.setProductPrice(product.getProductPrice());
		productDto.setProductPriceLog(product.getProductPriceLog());
		productDto.setProductStatus(product.getProductStatus());
		return productDto;
	}
}
