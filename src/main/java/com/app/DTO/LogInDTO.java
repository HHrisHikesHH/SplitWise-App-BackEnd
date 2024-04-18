package com.app.DTO;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LogInDTO {
	
	@NotEmpty(message = "email cannot be empty")
	private String email;
	
	@NotEmpty(message = "password cannot be empty")
	private String password;
	
}
