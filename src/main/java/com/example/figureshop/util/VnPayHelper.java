package com.example.figureshop.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.figureshop.dto.request.OrderDtoRequest;
import com.example.figureshop.entity.Order;
import com.example.figureshop.entity.OrderDetail;

import jakarta.servlet.http.HttpServletRequest;

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

	public String createPaymentUrl(OrderDtoRequest order, String ipAdd) throws UnsupportedEncodingException {
		Map<String, String> vnpParams = new HashMap<>();
		String vnp_Version = "2.1.0";
		String vnp_Command = "pay";
		String vnp_OrderType = "other";
		String vnp_TxnRef = String.valueOf(order.getOrderId());
		String vnp_Locale = "vn";
		String vnp_CurrCode = "VND";

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(calendar.getTime());
		calendar.add(Calendar.MINUTE, 15);
		String vnp_ExpireDate = formatter.format(calendar.getTime());

		vnpParams.put("vnp_Version", vnp_Version);
		vnpParams.put("vnp_Command", vnp_Command);
		vnpParams.put("vnp_TmnCode", vnpTmnCode);
		vnpParams.put("vnp_Amount", String.valueOf(order.getOrderTotal() * 100));
		vnpParams.put("vnp_CurrCode", vnp_CurrCode);
		vnpParams.put("vnp_IpAddr", ipAdd);
		vnpParams.put("vnp_Locale", vnp_Locale);
		vnpParams.put("vnp_OrderInfo", "Thanh toan cho don hang " + order.getOrderId());
		vnpParams.put("vnp_OrderType", vnp_OrderType);
		vnpParams.put("vnp_ReturnUrl", vnpReturnUrl);
		vnpParams.put("vnp_TxnRef", vnp_TxnRef);
		vnpParams.put("vnp_CreateDate", vnp_CreateDate);
		vnpParams.put("vnp_ExpireDate", vnp_ExpireDate);

		String queryUrl = VnPayUtils.buildQueryUrl(vnpParams, vnpHashSecret);
	
		return vnpPayUrl + "?" + queryUrl;

	}

	public boolean isValidResponse(Map<String, String> params) throws UnsupportedEncodingException {
		return VnPayUtils.verifyResponse(params, vnpHashSecret);
	}

	public Map<String, String> getParamVnPay(HttpServletRequest request) {
		Map<String, String> vnpParams = new HashMap<>();
		Map<String, String[]> fields = request.getParameterMap();

		for (Map.Entry<String, String[]> entry : fields.entrySet()) {
			String key = entry.getKey();
			String[] values = entry.getValue();
			if (values.length > 0) {
				vnpParams.put(key, values[0]);
			}
		}

		return vnpParams;

	}
}
