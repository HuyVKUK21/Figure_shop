package com.example.figureshop.dto.request;

import java.util.List;

public class CheckoutDtoRequest {
	private ShippingDtoRequest shippingDtoRequest;
	
	private List<CartItemDtoRequest> items;
	

	public ShippingDtoRequest getShippingDtoRequest() {
		return shippingDtoRequest;
	}

	public void setShippingDtoRequest(ShippingDtoRequest shippingDtoRequest) {
		this.shippingDtoRequest = shippingDtoRequest;
	}

	public List<CartItemDtoRequest> getItems() {
		return items;
	}

	public void setItems(List<CartItemDtoRequest> items) {
		this.items = items;
	}
	
	
	
}
