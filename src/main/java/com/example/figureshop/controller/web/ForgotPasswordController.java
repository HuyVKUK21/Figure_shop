package com.example.figureshop.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ForgotPasswordController {
	@GetMapping("/forgot-password")
	public String forgetPasswordPage() {
        return "web/forgot-password";  
    }
}
