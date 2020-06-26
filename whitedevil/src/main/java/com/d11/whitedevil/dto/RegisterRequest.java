package com.d11.whitedevil.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Serializable{
	
	private static final long serialVersionUID = 3846337967550645954L;
	
	private String email;
	private String userName;
	private String password;

}
