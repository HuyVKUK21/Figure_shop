package com.example.figureshop.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.figureshop.dto.request.UserDtoRequest;
import com.example.figureshop.dto.response.UserDtoResponse;
import com.example.figureshop.enums.ProviderEnum;
import com.example.figureshop.security.CustomUserDetails;
import com.example.figureshop.security.JwtTokenProvider;
import com.example.figureshop.service.IUserService;

@Controller
@RequestMapping("/user")
public class HomeController {
	@Autowired
	private IUserService userService;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/home")
	public String homePage() {
	    return "web/home";
	}


	@GetMapping("/contact")
	public String contactPage() {
		return "web/contact";
	}

}
