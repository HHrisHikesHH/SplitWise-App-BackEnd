package com.app.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;

import com.app.entity.Role;

import lombok.Data;

@Data
public class UserDTO {

	@NotEmpty(message = "firstName cannot be empty")
	private String firstName;

	@NotEmpty(message = "lastName cannot be empty")
	private String lastName;

	@NotEmpty(message = "email cannot be empty")
	@Email(message = "email invalid")
	private String email;

	@NotEmpty(message = "password cannot be empty")
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role = Role.USER;

}
