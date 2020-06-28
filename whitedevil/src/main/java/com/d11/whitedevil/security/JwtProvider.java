package com.d11.whitedevil.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.d11.whitedevil.entity.UserInfo;
import com.d11.whitedevil.exception.WDRuntimeException;

import io.jsonwebtoken.Jwts;

@Service
public class JwtProvider {
	/*
	 * First we have field called as KeyStore and Initialize this field inside the
	 * postconstuct block
	 */
	private KeyStore keyStore;

	@PostConstruct
	public void init() {

		try {
			
			keyStore = KeyStore.getInstance("JKS");
			InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
			keyStore.load(resourceAsStream, "secret".toCharArray());
			
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			throw new WDRuntimeException("Exception occured while loading keystore");
		}

	}

	public String generateToken(Authentication authentication) {

		User princial = (User) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject(princial.getUsername())
				.signWith(getPrivateKey())
				.compact();
	}

	private PrivateKey getPrivateKey() {
		try {
			return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
			throw new WDRuntimeException("Exception occured while retrieving public key from keystore", e);
		}
	}
}
