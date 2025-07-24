package com.example.figureshop.controller.web;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.figureshop.service.ICartService;
import com.example.figureshop.service.IOrderService;
import com.example.figureshop.service.IVnPayService;
import com.example.figureshop.util.AuthUtils;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/payment")
public class VnPayController {
	
	@Autowired
	private IVnPayService vnPayService;

	@Autowired
	private ICartService cartService;
	
	@Autowired
	private IOrderService orderService;

	@GetMapping("/return-vnpay")
	public String handleVNPayReturn(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String, String> vnpParams = orderService.getParamVnPay(request);
		Long userId = AuthUtils.getCurrentUserId();
		boolean isVaild = vnPayService.validatePayment(vnpParams);
		String responseCode = vnpParams.get("vnp_ResponseCode");
		String orderId = vnpParams.get("vnp_TxnRef");
		if (isVaild && "00".equals(responseCode)) {
			cartService.deleteCartByUserId(userId);
			orderService.updateOrderStatus(Long.parseLong(orderId));
			return "redirect:/user/home?paymentSuccess=true";
		} else {
			return "redirect:/payment";
		}
	}
}
