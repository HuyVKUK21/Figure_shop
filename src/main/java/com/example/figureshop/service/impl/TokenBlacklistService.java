package com.example.figureshop.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.figureshop.service.ITokenBlacklistService;

@Service
public class TokenBlacklistService implements ITokenBlacklistService{

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public void blacklistToken(String token, long millisUntilExpire) {
		redisTemplate.opsForValue().set("blacklist:" + token, "true", millisUntilExpire, TimeUnit.MILLISECONDS);
	}

	@Override
	public boolean isBlacklisted(String token) {
		 return Boolean.TRUE.equals(redisTemplate.hasKey("blacklist:" + token));
	}
	
}
