package com.example.figureshop.service;

public interface IMailService {
	void sendResetPasswordMail(String toEmail, String resetLink);
}
