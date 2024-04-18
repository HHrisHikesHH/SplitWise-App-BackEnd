package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.app.DTO.LogInDTO;
import com.app.DTO.SignInDTO;
import com.app.service.UserService;

@RestController
//@CrossOrigin("http://localhost:5173")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody SignInDTO userDto) {
//		System.out.println("inside Signin");
		System.err.println(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.signin(userDto));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LogInDTO userDto) {
		boolean response = userService.login(userDto);
		System.err.println(response + " " + userDto);
		if (response == true)
			return ResponseEntity.ok().body(userDto);
		else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userDto);

	}
	
	@GetMapping("/getMyDebts/{userId}")
	public ResponseEntity<?> getMyDebts(@PathVariable Integer userId) {
		return ResponseEntity.ok(userService.getMyDebts(userId));
	}
	
	@GetMapping("/getMyNetDebts/{userId}")
	public ResponseEntity<?> getAllDebts(@PathVariable Integer userId) {
		return ResponseEntity.ok(userService.getMyNetDebts(userId));
	}
	

}
