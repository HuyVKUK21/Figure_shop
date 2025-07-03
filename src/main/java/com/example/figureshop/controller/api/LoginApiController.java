package com.example.figureshop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.figureshop.dto.request.LoginDtoRequest;
import com.example.figureshop.dto.response.LoginDtoResponse;
import com.example.figureshop.security.CustomUserDetails;
import com.example.figureshop.security.JwtTokenProvider;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoginApiController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping("/login")
	public LoginDtoResponse authenticateUser(@Valid @RequestBody LoginDtoRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), 
						loginRequest.getPassword()));
		String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
		return new LoginDtoResponse(jwt);
	}
	
	
}
