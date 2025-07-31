package com.example.figureshop.service;

public interface ITokenBlacklistService {
	void blacklistToken(String token, long millisUntilExpire);
	boolean isBlacklisted(String token);
}
