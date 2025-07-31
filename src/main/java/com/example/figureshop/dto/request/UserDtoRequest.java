package com.example.figureshop.dto.request;

import com.example.figureshop.enums.ProviderEnum;

public class UserDtoRequest {
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userPhone;
	private ProviderEnum userProvider;
	
	

	public ProviderEnum getUserProvider() {
		return userProvider;
	}
	public void setUserProvider(ProviderEnum userProvider) {
		this.userProvider = userProvider;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	
}
