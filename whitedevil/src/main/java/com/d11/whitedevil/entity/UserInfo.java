package com.d11.whitedevil.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -3127371550038190960L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotBlank(message = "UserName is Required")
	private String username;
	
	@NotBlank(message="Password is Required")
	private String password;
	
	private String email;
	private Instant created;
	private boolean enabled;

}
