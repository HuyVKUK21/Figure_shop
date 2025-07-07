package com.example.figureshop.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CheckUserLogin {
	public static boolean isUserLoggedIn() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    return authentication != null &&
	           authentication.isAuthenticated() &&
	           !(authentication instanceof AnonymousAuthenticationToken);
	}
}
