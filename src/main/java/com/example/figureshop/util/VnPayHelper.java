package com.example.figureshop.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.figureshop.entity.Order;
import com.example.figureshop.entity.OrderDetail;

@Component
public class VnPayHelper {
	@Value("${vnpay.tmnCode}")
	private String vnpTmnCode;
	@Value("${vnpay.hashSecret}")
	private String vnpHashSecret;
	@Value("${vnpay.returnUrl}")
	private String vnpReturnUrl;
	@Value("${vnpay.paymentUrl}")
	private String vnpPayUrl;

	public String createPaymentUrl(OrderDetail orderDetail, String amount, String ipAdd) {
		Map<String, String> vnpParams = new HashMap<>();
		vnpParams.put("vnp_Version", "2.1.0");
		vnpParams.put("vnp_Command", "pay");
		vnpParams.put("vnp_TmnCode", vnpTmnCode);
		vnpParams.put("vnp_Amount", amount);
		vnpParams.put("vnp_CurrCode", "VND");
		vnpParams.put("vnp_ipAddr", ipAdd);
		vnpParams.put("vnp_Locale", "vn");
		vnpParams.put("vnp_OrderInfo", "Thanh toan cho don hang " + orderDetail.getOrderId());
		vnpParams.put("vnp_OrderType", "220003");
		vnpParams.put("vnp_ReturnUrl", vnpReturnUrl);
		vnpParams.put("vnp_TxnRef", String.valueOf(orderDetail.getOrderId()));
		vnpParams.put("vnp_CreateDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
		vnpParams.put("vnp_ExpireDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

		String queryUrl = VnPayUtils.buildQueryUrl(vnpParams, vnpHashSecret);
		return vnpPayUrl + "?" + queryUrl;

	}

	public boolean isValidResponse(Map<String, String> params) {
		return VnPayUtils.verifyResponse(params, vnpHashSecret);
	}
}
