package com.example.figureshop.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.figureshop.dto.request.ShippingDtoRequest;
import com.example.figureshop.dto.response.ShippingDtoResponse;
import com.example.figureshop.entity.Shipping;
import com.example.figureshop.mapper.CartMapper;
import com.example.figureshop.mapper.ShippingMapper;
import com.example.figureshop.repository.ShippingRepository;
import com.example.figureshop.service.IShippingService;

@Service
public class ShippingService implements IShippingService {

	@Autowired
	private ShippingRepository shippingRepository;

	@Override
	public List<ShippingDtoResponse> getUserShipping(Long userId) {
		List<Shipping> shippings = shippingRepository.findByUserId(userId);
		return shippings.stream().map(ShippingMapper::toDto).collect(Collectors.toList());

	}

}
