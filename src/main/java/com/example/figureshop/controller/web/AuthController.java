package com.example.figureshop.controller.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.figureshop.util.CheckUserLogin;

@Controller
@RequestMapping
public class AuthController {
	
	@GetMapping("/login")
	public String loginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(CheckUserLogin.isUserLoggedIn()) {
			return "redirect:/user/home";
		}
        return "web/login";  
    }
	
	@GetMapping("/register")
	public String registerPage() {
        return "web/register";  
    }
	
	@GetMapping("/forgot-password")
	public String forgetPasswordPage() {
        return "web/forgot-password";  
    }
	
}
