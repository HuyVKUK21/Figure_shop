package com.example.figureshop.controller.api;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.figureshop.dto.request.LoginDtoRequest;
import com.example.figureshop.dto.request.UserDtoRequest;
import com.example.figureshop.dto.response.LoginDtoResponse;
import com.example.figureshop.response.ApiResponse;
import com.example.figureshop.security.CustomUserDetails;
import com.example.figureshop.security.JwtTokenProvider;
import com.example.figureshop.service.IProductService;
import com.example.figureshop.service.IUserService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthApiController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private IUserService userService;

	@PostMapping("/login")
	public ResponseEntity<LoginDtoResponse> authenticateUser(@Valid @RequestBody LoginDtoRequest loginRequest, HttpServletResponse response) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
		ResponseCookie cookie = ResponseCookie.from("jwtToken", jwt)
				.httpOnly(true).secure(true).path("/")
				.maxAge(Duration.ofDays(1))
				.sameSite("Strict").build();
		response.setHeader("Set-Cookie", cookie.toString());
		return ResponseEntity.ok(new LoginDtoResponse(jwt));

	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> createUser(@RequestBody UserDtoRequest registerDtoRequest) {
		userService.createUser(registerDtoRequest);
		ApiResponse<String> response = ApiResponse.created("Success", null);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}


}
