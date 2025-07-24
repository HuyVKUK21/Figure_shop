package com.example.figureshop.service;

import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.figureshop.dto.request.CartItemDtoRequest;
import com.example.figureshop.dto.request.CheckoutDtoRequest;
import com.example.figureshop.dto.request.ShippingDtoRequest;
import com.example.figureshop.dto.response.OrderDtoResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface IOrderService {
	OrderDtoResponse paymentItem(Long userId, CheckoutDtoRequest checkoutDtoRequest);
	OrderDtoResponse getInfoByOrderId(Long orderId);
	Map<String, String> getParamVnPay(HttpServletRequest request);

	OrderDtoResponse updateOrderStatus(Long orderId);

}
