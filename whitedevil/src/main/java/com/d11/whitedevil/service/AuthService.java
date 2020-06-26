package com.d11.whitedevil.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.d11.whitedevil.dto.RegisterRequest;
import com.d11.whitedevil.entity.UserInfo;
import com.d11.whitedevil.repository.UserInfoRepository;

@Service
public class AuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserInfoRepository userInfoRepository;

	public void signUp(RegisterRequest registerResquest) {

		UserInfo user = new UserInfo();
		user.setUsername(registerResquest.getUserName());
		user.setPassword(passwordEncoder.encode(registerResquest.getPassword()));
		user.setEmail(registerResquest.getEmail());
		user.setCreated(Instant.now());
		user.setEnabled(false);

		userInfoRepository.save(user);

	}
}
