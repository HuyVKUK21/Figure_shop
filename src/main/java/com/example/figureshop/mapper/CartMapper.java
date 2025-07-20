package com.example.figureshop.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.figureshop.dto.request.AddCartDtoRequest;
import com.example.figureshop.dto.request.UpdateCartDtoRequest;
import com.example.figureshop.dto.response.CartDtoResponse;
import com.example.figureshop.dto.response.ProductDtoResponse;
import com.example.figureshop.entity.Cart;
import com.example.figureshop.entity.CategoryProduct;
import com.example.figureshop.entity.Product;
import com.example.figureshop.entity.ProductImage;
import com.example.figureshop.entity.User;
import com.example.figureshop.repository.CartRepository;

public class CartMapper {
	
	public static Cart addCartRequestDto(AddCartDtoRequest addCartDtoRequest) {
		Cart cart = new Cart();
		CategoryProduct categoryProduct = new CategoryProduct();
		Product product = new Product();
		User user = new User();
		categoryProduct.setCategoryId(addCartDtoRequest.getCategoryId());
		product.setProductId(addCartDtoRequest.getProductId());
		user.setUserId(addCartDtoRequest.getUserId());
		cart.setCategoryProduct(categoryProduct);
		cart.setProduct(product);
		cart.setUser(user);
		cart.setQuantity(addCartDtoRequest.getQuantity());	
		return cart;
	}
	
	public static Cart updateCartRequestDto(UpdateCartDtoRequest updateCartDtoRequest) {
		Cart cart = new Cart();
		cart.setCartId(updateCartDtoRequest.getCartId());
		cart.setQuantity(updateCartDtoRequest.getQuantity());
		return cart;
	}
	
	
	
	public static CartDtoResponse toDto(Cart cart) {
		CartDtoResponse cartDtoResponse = new CartDtoResponse();
		cartDtoResponse.setCartId(cart.getCartId());
		cartDtoResponse.setCategoryId(cart.getCategoryProduct().getCategoryId());
		cartDtoResponse.setProductId(cart.getProduct().getProductId());
		cartDtoResponse.setUserId(cart.getUser().getUserId());
		cartDtoResponse.setQuantity(cart.getQuantity());
		cartDtoResponse.setProductImage(cart.getProduct().getProductImages());
		cartDtoResponse.setProductName(cart.getProduct().getProductName());
		cartDtoResponse.setProductPrice(cart.getProduct().getProductPrice());		
		return cartDtoResponse;
	}
	
	
	
}
