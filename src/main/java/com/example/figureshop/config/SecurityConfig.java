package com.example.figureshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.figureshop.response.ApiResponse;
import com.example.figureshop.security.JwtAuthenticationFilter;
import com.example.figureshop.service.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	private final UserService userService;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	public SecurityConfig(UserService userService, JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.userService = userService;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .cors(cors -> {})
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/login", "/api/login", "/api/register", "/register", 
	                "/forgot-password", "/WEB-INF/**", "/template/**",
	                "/resources/**", "/static/**", "/css/**", "/js/**")
	            .permitAll()
	            .anyRequest().authenticated()
	        )
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .exceptionHandling(exc -> exc
	            .authenticationEntryPoint((request, response, authException) -> {
	                if (request.getRequestURI().startsWith("/api/")) {
	                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	                    response.setContentType("application/json");
	                    
	                    ApiResponse<?> errorResponse = ApiResponse.error(
	                        HttpStatus.UNAUTHORIZED.value(), 
	                        "Unauthorized access"
	                    );
	                    	                  
	                    ObjectMapper mapper = new ObjectMapper();
	                    String jsonResponse = mapper.writeValueAsString(errorResponse);
	                    
	                    response.getWriter().write(jsonResponse);
	                } else {
	                    response.sendRedirect("/login");
	                }
	            })
	        )
	        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

}
