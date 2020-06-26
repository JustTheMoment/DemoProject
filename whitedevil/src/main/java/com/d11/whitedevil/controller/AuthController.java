package com.d11.whitedevil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d11.whitedevil.dto.RegisterRequest;
import com.d11.whitedevil.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/signup")
	public void signUp(@RequestBody RegisterRequest registerRequest) {
		authService.signUp(registerRequest);
	}
}
