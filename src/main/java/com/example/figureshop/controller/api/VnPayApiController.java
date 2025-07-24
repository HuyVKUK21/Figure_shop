package com.example.figureshop.controller.api;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.figureshop.dto.request.OrderDtoRequest;
import com.example.figureshop.dto.response.CartDtoResponse;
import com.example.figureshop.dto.response.OrderDtoResponse;
import com.example.figureshop.mapper.OrderMapper;
import com.example.figureshop.response.ApiResponse;
import com.example.figureshop.service.IOrderService;
import com.example.figureshop.service.IVnPayService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/payment")
public class VnPayApiController {
	@Autowired
	private IVnPayService vnPayService;
	
	@Autowired
	private IOrderService orderService;
	
	@GetMapping("/vnpay/{orderId}")
	public ResponseEntity<ApiResponse<String>> getVNPayPaymentUrl(@PathVariable Long orderId, HttpServletRequest request) throws UnsupportedEncodingException {
	    OrderDtoResponse orderResponse = orderService.getInfoByOrderId(orderId);
	    OrderDtoRequest order = OrderMapper.toRequestDto(orderResponse);
	    String ipAddr = request.getRemoteAddr();
		String vnpayUrl = vnPayService.createVNPayPaymentUrl(order, ipAddr);
		ApiResponse<String> response = ApiResponse.success(vnpayUrl);
	    return ResponseEntity.ok(response);
	}

}
