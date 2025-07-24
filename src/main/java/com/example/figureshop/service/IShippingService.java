package com.example.figureshop.service;

import java.util.List;

import com.example.figureshop.dto.request.ShippingDtoRequest;
import com.example.figureshop.dto.response.ShippingDtoResponse;

public interface IShippingService {
	List<ShippingDtoResponse> getUserShipping(Long userId);
}
