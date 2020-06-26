package com.d11.whitedevil.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail implements Serializable {

	private static final long serialVersionUID = -2492744213876580000L;

	private String subject;
	private String recipient;
	private String body;

}
