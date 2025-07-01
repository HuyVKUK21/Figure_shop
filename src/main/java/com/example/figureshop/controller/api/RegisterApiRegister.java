package com.example.figureshop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.figureshop.dto.request.RegisterDtoRequest;
import com.example.figureshop.response.ApiResponse;
import com.example.figureshop.service.IUserService;

@RestController
@RequestMapping("/api")
public class RegisterApiRegister {
	@Autowired
	private IUserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> createUser(@RequestBody RegisterDtoRequest registerDtoRequest) {
		userService.createUser(registerDtoRequest);
		ApiResponse<String> response = ApiResponse.created("Success", null);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
}
