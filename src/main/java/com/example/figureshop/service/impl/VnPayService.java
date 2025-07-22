package com.example.figureshop.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.figureshop.entity.Order;
import com.example.figureshop.entity.OrderDetail;
import com.example.figureshop.service.IVnPayService;
import com.example.figureshop.util.VnPayHelper;

@Service
public class VnPayService implements IVnPayService {
	
	@Autowired
	private VnPayHelper vnPayHelper;
	
	
	@Override
	public String createVNPayPaymentUrl(OrderDetail orderDetail, String amount, String clientIp) {
		// TODO Auto-generated method stub
		return vnPayHelper.createPaymentUrl(orderDetail,amount, clientIp);
	}

	@Override
	public boolean validatePayment(Map<String, String> params) {
		return vnPayHelper.isValidResponse(params);
	}

}
