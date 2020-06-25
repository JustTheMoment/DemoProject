package com.d11.whitedevil.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="UserInfo")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -3127371550038190960L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;
	
	@NotBlank(message = "UserName is Required")
	@Column(name="user_name")
	private String username;
	
	@NotBlank(message="Password is Required")
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="created_timestamp")
	private Instant created;
	
	@Column(name="is_enabled")
	private boolean enabled;

}
