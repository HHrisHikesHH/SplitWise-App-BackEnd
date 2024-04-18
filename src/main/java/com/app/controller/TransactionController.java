package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.DTO.TransactionDTO;
import com.app.entity.Group;
import com.app.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/getAllExpenses/{groupId}")
	public ResponseEntity<?> getAllExpenses
				(@PathVariable Integer groupId) {
		return ResponseEntity
				.ok(transactionService.getAllExpenses(groupId));
	}
	
	
	@PostMapping("/addExpenses")
	public ResponseEntity<?> addExpenses(@RequestBody TransactionDTO transactionDTO) {
		System.err.println(transactionDTO);
		return ResponseEntity
				.ok(transactionService.addExpenses(transactionDTO));
	}
}
