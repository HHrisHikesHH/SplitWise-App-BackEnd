package com.app.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class TransactionDTO {
	
	private Integer txId;
	
	private BigDecimal amount;
	
	private String transactionName;

	private Integer paidById;
	
	private List<Integer> membersInvolvedId = new ArrayList<>();

	private Integer groupId;
	
	private String groupName;
	
	private LocalDateTime currentDateTime = LocalDateTime.now();
}

/*
 
 {
  "amount": 100.00,
  "transactionName": "Sample Transaction",
  "paidBy": 1,
  "membersInvolved": [2, 3, 4],
  "group": 1,
  "currentDateTime": "2022-04-14T10:30:00"
}

*/
