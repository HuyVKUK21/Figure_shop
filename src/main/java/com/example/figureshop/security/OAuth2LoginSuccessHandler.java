package com.example.figureshop.security;

import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.figureshop.dto.request.UserDtoRequest;
import com.example.figureshop.dto.response.UserDtoResponse;
import com.example.figureshop.enums.ProviderEnum;
import com.example.figureshop.service.IUserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	private IUserService userService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		OAuth2User principal = (OAuth2User) authentication.getPrincipal();
		String email = principal.getAttribute("email");
		String name = principal.getAttribute("name");
		String phone = principal.getAttribute("phone_number");
		String dummyPassword = UUID.randomUUID().toString();
		Long userIdExist = userService.checkUserExist(email);
		String jwt = "";
		if (userIdExist != null) {
			CustomUserDetails userDetails = (CustomUserDetails) userService.loadUserById(userIdExist);
			jwt = jwtTokenProvider.generateToken(userDetails);
		} else {
			UserDtoRequest dto = new UserDtoRequest();
			dto.setUserEmail(email);
			dto.setUserName(name);
			dto.setUserPhone(phone);
			dto.setUserPassword(dummyPassword);
			dto.setUserProvider(ProviderEnum.GOOGLE);
			UserDtoResponse userRes = userService.createUser(dto);
			CustomUserDetails userDetails = (CustomUserDetails) userService.loadUserById(userRes.getUserId());
			jwt = jwtTokenProvider.generateToken(userDetails);
		}

		ResponseCookie cookie = ResponseCookie.from("jwtToken", jwt).httpOnly(true).secure(true).path("/")
				.maxAge(Duration.ofDays(1)).sameSite("Strict").build();
		response.setHeader("Set-Cookie", cookie.toString());

		response.sendRedirect("/user/home");
	}
}
