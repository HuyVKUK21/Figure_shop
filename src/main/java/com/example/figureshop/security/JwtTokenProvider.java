package com.example.figureshop.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.nio.charset.StandardCharsets;

@Component
public class JwtTokenProvider {

	private final SecretKey key;
	private final long expirationTime;


	 public JwtTokenProvider(
	            @Value("${jwt.secret}") String secretKey,
	            @Value("${jwt.expiration}") long expirationTime
	    ) {
	        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	        this.expirationTime = expirationTime;
	    }
	public String generateToken(CustomUserDetails userDetails) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expirationTime);

		return Jwts.builder().subject(Long.toString(userDetails.getUser().getUserId())).issuedAt(now)
				.expiration(expiryDate).signWith(key).compact();
	}

	public Long getUserIdFromJWT(String token) {
		JwtParser parser = Jwts.parser().verifyWith(key).build();
		Claims claims = parser.parseSignedClaims(token).getPayload();
		return Long.parseLong(claims.getSubject());
	}

	public boolean validateToken(String token) {
		try {
			JwtParser parser = Jwts.parser().verifyWith(key).build();
			parser.parseSignedClaims(token);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public Date extractExpiration(String token) {
		JwtParser parser = Jwts.parser().setSigningKey(key).build();
		return parser.parseClaimsJws(token).getBody().getExpiration();
	}
}
