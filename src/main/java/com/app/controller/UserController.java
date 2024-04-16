package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.app.DTO.UserDTO;
import com.app.service.UserService;

@RestController
//@CrossOrigin("http://localhost:5173")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody UserDTO userDto) {
//		System.out.println("inside Signin");
		System.err.println(userDto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userDto);
//				.body(userService.signin(userDto));
	}
	

}
