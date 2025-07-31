package com.example.figureshop.dto.request;

public class ResetPasswordDtoRequest {
	private String lastestPassword;
	private String token;
	public String getLastestPassword() {
		return lastestPassword;
	}
	public void setLastestPassword(String lastestPassword) {
		this.lastestPassword = lastestPassword;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
