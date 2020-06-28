package com.d11.whitedevil.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse implements Serializable{

	
	private static final long serialVersionUID = -128468159171922262L;
	
	private String authenticationToken;
	private String userName;
}
