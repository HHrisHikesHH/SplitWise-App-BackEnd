package com.app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.app.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping("/getAllGroups")
	public ResponseEntity<?> getAllGroups() {
		return ResponseEntity.ok(groupService.getAllGroups());
	}
	
	
	

}
