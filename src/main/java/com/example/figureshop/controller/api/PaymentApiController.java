package com.example.figureshop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.figureshop.dto.request.CheckoutDtoRequest;
import com.example.figureshop.dto.response.CartDtoResponse;
import com.example.figureshop.dto.response.OrderDtoResponse;
import com.example.figureshop.entity.Order;
import com.example.figureshop.entity.OrderDetail;
import com.example.figureshop.response.ApiResponse;
import com.example.figureshop.service.IOrderService;
import com.example.figureshop.util.AuthUtils;

@RestController
@RequestMapping("/api/")

public class PaymentApiController {
	@Autowired
	private IOrderService orderService;
	
	@PostMapping("/payment")
	public ResponseEntity<ApiResponse<OrderDtoResponse>> paymentProduct(@RequestBody CheckoutDtoRequest checkoutDtoRequest) {
		Long userId = AuthUtils.getCurrentUserId();
		OrderDtoResponse orderDtoResponse = orderService.paymentItem(userId, checkoutDtoRequest);
		ApiResponse<OrderDtoResponse> response = ApiResponse.success(orderDtoResponse);
		return ResponseEntity.ok(response);
	}

}
