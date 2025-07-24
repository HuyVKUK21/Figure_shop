package com.example.figureshop.mapper;

import com.example.figureshop.dto.request.ShippingDtoRequest;
import com.example.figureshop.dto.response.ShippingDtoResponse;
import com.example.figureshop.entity.Shipping;

public class ShippingMapper {
	public static Shipping toEntity(ShippingDtoRequest dto) {
		Shipping shipping = new Shipping();
		shipping.setShippingName(dto.getShippingName());
		shipping.setShippingAddress(dto.getShippingAddress());
		shipping.setShippingPhone(dto.getShippingPhone());
		return shipping;
	}
	
	public static ShippingDtoResponse toDto(Shipping shipping) {
		ShippingDtoResponse shippingDtoResponse = new ShippingDtoResponse();
		shippingDtoResponse.setShippingId(shipping.getShippingId());
		shippingDtoResponse.setUserId(shipping.getUserId());
		shippingDtoResponse.setShippingName(shipping.getShippingName());
		shippingDtoResponse.setShippingAddress(shipping.getShippingAddress());
		shippingDtoResponse.setShippingPhone(shipping.getShippingPhone());
		return shippingDtoResponse;
	}
}
