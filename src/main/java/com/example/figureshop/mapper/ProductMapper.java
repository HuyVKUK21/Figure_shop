package com.example.figureshop.mapper;

import com.example.figureshop.dto.response.ProductDtoResponse;
import com.example.figureshop.entity.BrandProduct;
import com.example.figureshop.entity.CategoryProduct;
import com.example.figureshop.entity.Product;

public class ProductMapper {
	
//	public static Product toEntity(ProductDtoResponse dto) {
//		Product product = new Product();
//		BrandProduct brand = new BrandProduct();
//		CategoryProduct category = new CategoryProduct();
//		product.setProductId(dto.getProductId());
//		category.setCategoryId(dto.getCategoryId());
//		brand.setBrandId(dto.getBrandId());
//		product.setProductImages(dto.setProductImage(null));
//		product.setProductName(dto.getProductName());
//		product.setProductDesc(dto.getProductDesc());
//		product.setProductSeries(dto.getProductSeries());
//		product.setProductSize(dto.getProductSize());
//		product.setProductProportion(dto.getProductProportion());
//		product.setProductDate(dto.getProductDate());
//		product.setProductPrice(dto.getProductPrice());
//		product.setProductPriceLog(dto.getProductPriceLog());
//		product.setProductStatus(dto.getProductStatus());	
//		return product;
//	}
	
	public static ProductDtoResponse toDto(Product product) {
		ProductDtoResponse productDto = new ProductDtoResponse();
		productDto.setProductId(product.getProductId());
		productDto.setCategoryId(product.getCategoryProduct().getCategoryId());
		productDto.setBrandId(product.getBrandProduct().getBrandId());
		productDto.setBrandName(product.getBrandProduct().getBrandName());
		productDto.setCategoryName(product.getCategoryProduct().getCategoryName());
		productDto.setProductImage(product.getProductImages());
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
