package com.app.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "transaction_tbl")
@Data
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer txId;
	
	private BigDecimal amount;
	
	@Column(name = "txName")
	private String transactionName;
	
	@ManyToOne
	@JoinColumn(name = "paid_by_id")
	private User paidBy;
	
	@ManyToMany
	private Set<User> membersInvolved = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;
	
	private LocalDateTime currentDateTime = LocalDateTime.now();
	
}
