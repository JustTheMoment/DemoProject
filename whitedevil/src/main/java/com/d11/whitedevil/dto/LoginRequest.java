package com.d11.whitedevil.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Serializable{
	
	private static final long serialVersionUID = 1305256299214371696L;
	
	private String userName;
	private String password;

}
