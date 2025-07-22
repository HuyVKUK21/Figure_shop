package com.example.figureshop.mapper;

import com.example.figureshop.dto.response.OrderDtoResponse;
import com.example.figureshop.entity.Order;

public class OrderMapper {
	public static OrderDtoResponse toDto(Order order) {
		OrderDtoResponse dto = new OrderDtoResponse();
		dto.setOrderId(order.getOrderId());
		dto.setOrderTotal(order.getOrderTotal());
		dto.setOrderStatus(order.getOrderStatus());
		return dto;
	}
}
