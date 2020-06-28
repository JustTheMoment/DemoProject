package com.d11.whitedevil.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d11.whitedevil.dto.AuthenticationResponse;
import com.d11.whitedevil.dto.LoginRequest;
import com.d11.whitedevil.dto.NotificationEmail;
import com.d11.whitedevil.dto.RegisterRequest;
import com.d11.whitedevil.entity.UserInfo;
import com.d11.whitedevil.entity.VerificationToken;
import com.d11.whitedevil.exception.WDRuntimeException;
import com.d11.whitedevil.repository.UserInfoRepository;
import com.d11.whitedevil.repository.VerificationTokenRepository;
import com.d11.whitedevil.security.JwtProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserInfoRepository userInfoRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;
	/*
	 * This is an interface.An interface we do not mention explictly what kind of
	 * bean to create Spring throws an exception.So we create a bean inside the
	 * securityConfig.class
	 */
	private final AuthenticationManager authenticationManager;
	private JwtProvider jwtProvider;

	@Transactional
	public void signUp(RegisterRequest registerResquest) {

		UserInfo user = new UserInfo();
		user.setUserName(registerResquest.getUserName());
		user.setPassword(passwordEncoder.encode(registerResquest.getPassword()));
		user.setEmail(registerResquest.getEmail());
		user.setCreated(Instant.now());
		user.setEnabled(false);

		userInfoRepository.save(user);

		String token = generateVerificationToken(user);

		NotificationEmail notificationEmail = new NotificationEmail();
		notificationEmail.setSubject("Please Active your Account");
		notificationEmail.setRecipient(user.getEmail());
		notificationEmail.setBody(
				"Thank you for signing up to White Devil, " + "please click on the below url to active our account "
						+ "http://localhost:8080/api/auth/accountVerification/" + token);

		mailService.sendMail(notificationEmail);

	}

	public String generateVerificationToken(UserInfo user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return token;
	}

	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
		verificationToken.orElseThrow(() -> new WDRuntimeException("Invalid Token"));
		fetchUserAndEnable(verificationToken.get());
	}

	@Transactional
	public void fetchUserAndEnable(VerificationToken verificationToken) {
		String userName = verificationToken.getUser().getUserName();
		UserInfo user = userInfoRepository.findByUserName(userName)
				.orElseThrow(() -> new WDRuntimeException("User not found with name " + userName));
		user.setEnabled(true);
		userInfoRepository.save(user);
	}

	/*
	 * This method contains the logic to create username password authentication
	 * token
	 */
	public AuthenticationResponse login(LoginRequest loginRequest) {

		Authentication authenticate= authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
		/*
		 * If u want to check it if the user is logged in or not u check look at into the
		 * security context for authenticate object
		 * if u find the object, u can show user is logged in or else not logged in
		 */
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		
		String token=jwtProvider.generateToken(authenticate);
		return new AuthenticationResponse(token,loginRequest.getUserName());

	}
}
