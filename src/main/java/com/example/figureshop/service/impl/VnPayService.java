package com.example.figureshop.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.figureshop.dto.request.OrderDtoRequest;
import com.example.figureshop.entity.Order;
import com.example.figureshop.entity.OrderDetail;
import com.example.figureshop.service.IVnPayService;
import com.example.figureshop.util.VnPayHelper;

@Service
public class VnPayService implements IVnPayService {
	
	@Autowired
	private VnPayHelper vnPayHelper;
	
	
	@Override
	public String createVNPayPaymentUrl(OrderDtoRequest orderDtoRequest, String clientIp) throws UnsupportedEncodingException {
		return vnPayHelper.createPaymentUrl(orderDtoRequest, clientIp);
	}

	@Override
	public boolean validatePayment(Map<String, String> params) throws UnsupportedEncodingException {
		return vnPayHelper.isValidResponse(params);
	}

}
