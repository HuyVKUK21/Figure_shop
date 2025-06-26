package com.example.figureshop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.figureshop.dto.UserRegisterRequestDTO;
import com.example.figureshop.dto.UserResponseDTO;
import com.example.figureshop.response.ApiResponse;
import com.example.figureshop.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class ExampleApiController {
	@Autowired
	private IUserService userService;

	@PostMapping
	public ResponseEntity<ApiResponse<String>> create(@RequestBody UserRegisterRequestDTO dto) {
		userService.createUser(dto);
		ApiResponse<String> response = ApiResponse.created("Thành công", null);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAll() {
		List<UserResponseDTO> users = userService.getAllUsers();
		ApiResponse<List<UserResponseDTO>> response = ApiResponse.success(users);
		return ResponseEntity.ok(response);
	}
}
