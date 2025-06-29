package com.example.figureshop.mapper;

import com.example.figureshop.dto.ProductDto;
import com.example.figureshop.entity.Product;

public class ProductMapper {
	
	public static Product toEntity(ProductDto dto) {
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
		product.setCreatedAt(dto.getCreatedAt());
		product.setUpdatedAt(dto.getUpdatedAt());
		return product;
	}
	
	public static ProductDto toDto(Product product) {
		ProductDto productDto = new ProductDto();
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
		productDto.setCreatedAt(product.getCreatedAt());
		productDto.setUpdatedAt(product.getUpdatedAt());
		return productDto;
	}
}
