package com.example.figureshop.service;

import java.util.Map;

import com.example.figureshop.entity.Order;
import com.example.figureshop.entity.OrderDetail;

public interface IVnPayService {
	String createVNPayPaymentUrl(OrderDetail orderDetail, String amount, String clientIp);
    boolean validatePayment(Map<String, String> params);
}
