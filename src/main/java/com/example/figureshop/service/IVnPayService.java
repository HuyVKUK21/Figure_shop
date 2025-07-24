package com.example.figureshop.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.example.figureshop.dto.request.OrderDtoRequest;
import com.example.figureshop.entity.Order;
import com.example.figureshop.entity.OrderDetail;

public interface IVnPayService {
	String createVNPayPaymentUrl(OrderDtoRequest orderDtoRequest, String clientIp) throws UnsupportedEncodingException;
    boolean validatePayment(Map<String, String> params);
}
