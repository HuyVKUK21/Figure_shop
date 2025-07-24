package com.example.figureshop.dto.request;

import java.util.List;

public class CheckoutDtoRequest {
	private Long shippingId;
	private List<CartItemDtoRequest> items;

	public Long getShippingId() {
		return shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}

	public List<CartItemDtoRequest> getItems() {
		return items;
	}

	public void setItems(List<CartItemDtoRequest> items) {
		this.items = items;
	}

}
