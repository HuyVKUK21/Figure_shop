package com.example.figureshop.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.figureshop.exception.NotFoundException;
import com.example.figureshop.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionController {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiResponse<?>> handleNotFound(NotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(404, ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> handleAll(Exception ex) {
		return ResponseEntity.status(500).body(ApiResponse.error(500, "Internal Server Error"));
	}
}
