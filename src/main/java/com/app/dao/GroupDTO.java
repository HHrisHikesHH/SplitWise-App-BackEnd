package com.app.dao;

import java.util.List;

import lombok.Data;

@Data
public class GroupDTO {
	
	private Integer groupId;

	private String groupName;
	
	private List<Integer> groupmemberID; // for receiving
	
	private List<String> groupmemberNames;// for sending 
}
