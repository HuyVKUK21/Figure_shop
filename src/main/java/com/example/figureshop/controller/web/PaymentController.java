package com.example.figureshop.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PaymentController {
	@GetMapping("/payment")
	public String paymentPage() {
		return "web/payment";
	}
}
