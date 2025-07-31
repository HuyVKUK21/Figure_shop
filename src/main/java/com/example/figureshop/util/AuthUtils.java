package com.example.figureshop.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.figureshop.security.CustomUserDetails;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class AuthUtils {
	public static Long getCurrentUserId() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();	    
	    return user.getUser().getUserId();
	}
	
	public static boolean isUserLoggedIn() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authentication != null &&
	           authentication.isAuthenticated() &&
	           !(authentication instanceof AnonymousAuthenticationToken);
	}
	
	public static String extractToken(HttpServletRequest request) {
	    String header = request.getHeader("Authorization");
	    if (header != null && header.startsWith("Bearer ")) {
	        return header.substring(7);
	    }

	    if (request.getCookies() != null) {
	        for (Cookie cookie : request.getCookies()) {
	            if ("jwtToken".equals(cookie.getName())) {
	                return cookie.getValue();
	            }
	        }
	    }

	    return null;
	}
}
