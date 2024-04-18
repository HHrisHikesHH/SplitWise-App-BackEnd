package com.app.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
	private List<User> membersInvolved = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;
	
	private LocalDateTime currentDateTime = LocalDateTime.now();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(paidBy, other.paidBy) && Objects.equals(txId, other.txId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(paidBy, txId);
	}
	
	
	
}
