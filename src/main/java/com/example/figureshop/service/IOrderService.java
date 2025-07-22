package com.example.figureshop.service;

import com.example.figureshop.dto.request.CartItemDtoRequest;
import com.example.figureshop.dto.request.CheckoutDtoRequest;
import com.example.figureshop.dto.request.ShippingDtoRequest;
import com.example.figureshop.dto.response.OrderDtoResponse;

public interface IOrderService {
	OrderDtoResponse paymentItem(Long userId, CheckoutDtoRequest checkoutDtoRequest);
}
