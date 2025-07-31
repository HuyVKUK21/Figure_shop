package com.example.figureshop.controller.api;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.figureshop.dto.request.ForgotPasswordDtoRequest;
import com.example.figureshop.dto.request.LoginDtoRequest;
import com.example.figureshop.dto.request.ResetPasswordDtoRequest;
import com.example.figureshop.dto.request.UserDtoRequest;
import com.example.figureshop.dto.response.CartDtoResponse;
import com.example.figureshop.dto.response.LoginDtoResponse;
import com.example.figureshop.dto.response.UserDtoResponse;
import com.example.figureshop.entity.User;
import com.example.figureshop.response.ApiResponse;
import com.example.figureshop.security.CustomUserDetails;
import com.example.figureshop.security.JwtTokenProvider;
import com.example.figureshop.service.IMailService;
import com.example.figureshop.service.IProductService;
import com.example.figureshop.service.ITokenBlacklistService;
import com.example.figureshop.service.IUserService;
import com.example.figureshop.util.AuthUtils;

import io.jsonwebtoken.io.IOException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthApiController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private IUserService userService;

	@Autowired
	private ITokenBlacklistService tokenBlacklistService;

	@Autowired
	private IMailService mailService;

	@Value("${spring.mail.host}")
	private String mailHost;

	@PostConstruct
	public void printHost() {
		System.out.println("MAIL HOST: " + mailHost); // Phải là smtp.gmail.com
	}

	@PostMapping("/login")
	public ResponseEntity<LoginDtoResponse> authenticateUser(@Valid @RequestBody LoginDtoRequest loginRequest,
			HttpServletResponse response) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
		ResponseCookie cookie = ResponseCookie.from("jwtToken", jwt).httpOnly(true).secure(true).path("/")
				.maxAge(Duration.ofDays(1)).sameSite("Strict").build();
		response.setHeader("Set-Cookie", cookie.toString());
		return ResponseEntity.ok(new LoginDtoResponse(jwt));

	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> createUser(@RequestBody UserDtoRequest registerDtoRequest) {
		userService.createUser(registerDtoRequest);
		ApiResponse<String> response = ApiResponse.created("Success", null);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@PostMapping("/logout")
	public ResponseEntity<ApiResponse<String>> logoutUser(HttpServletRequest request, HttpServletResponse response)
			throws java.io.IOException {
		String token = AuthUtils.extractToken(request);
		if (token != null) {
			long remaining = tokenProvider.extractExpiration(token).getTime() - System.currentTimeMillis();
			tokenBlacklistService.blacklistToken(token, remaining);
		}

		Cookie jwtCookie = new Cookie("jwtToken", null);
		jwtCookie.setHttpOnly(true);
		jwtCookie.setPath("/");
		jwtCookie.setMaxAge(0);
		response.addCookie(jwtCookie);

		Cookie sessionCookie = new Cookie("JSESSIONID", null);
		sessionCookie.setPath("/");
		sessionCookie.setMaxAge(0);
		response.addCookie(sessionCookie);
		ApiResponse<String> apiResponse = ApiResponse.success("Sign out Successfully");

		return ResponseEntity.ok(apiResponse);
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<ApiResponse<?>> forgotPassword(@RequestBody ForgotPasswordDtoRequest request) {
		Optional<UserDtoResponse> userOpt = userService.findByUserEmail(request.getEmail());

		if (userOpt.isEmpty()) {
			ApiResponse<?> response = ApiResponse.error(HttpStatus.NOT_FOUND.value(), "Email Reset Password sent");
			return ResponseEntity.ok(response);
		}
		Long userId = userOpt.get().getUserId();
		CustomUserDetails userDetails = (CustomUserDetails) userService.loadUserById(userId);
		String token = tokenProvider.generateToken(userDetails);
		String resetLink = "http://localhost:8080/reset-password?token=" + token;

		mailService.sendResetPasswordMail(request.getEmail(), resetLink);
		ApiResponse<String> response = ApiResponse.success("Email Reset Password sent");
		return ResponseEntity.ok(response);
	}

	@GetMapping("/reset-password")
	public ResponseEntity<ApiResponse<?>> forgotPassword(@RequestBody ResetPasswordDtoRequest request) {
		boolean checkToken = tokenProvider.validateToken(request.getToken());
		if (checkToken) {
			Long userId = tokenProvider.getUserIdFromJWT(request.getToken());
			userService.updatePasswordForgot(userId, request.getLastestPassword());
			ApiResponse<String> response = ApiResponse.success("Change password successfully");
			return ResponseEntity.ok(response);
		} else {
			ApiResponse<?> response = ApiResponse.error(HttpStatus.NOT_FOUND.value(), "Error changing password");
			return ResponseEntity.ok(response);
		}
	}

}
