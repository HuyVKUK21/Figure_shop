package com.example.figureshop.dto.response;

public class LoginDtoResponse {
	private String token;

	public LoginDtoResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
