package com.example.figureshop.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.figureshop.security.CustomUserDetails;

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
}
