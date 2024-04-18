package com.app.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "User_tbl")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@Column(name = "fname")
	private String firstName; 
	
	@Column(name = "lname")
	private String lastName; 
	
	private String email;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role = Role.USER;
	
	@ManyToMany(mappedBy = "members")
	private Set<Group> myGroups= new HashSet<>();
	// we can do transaction in a particular group only
	
	@OneToMany(mappedBy = "paidBy")
    private List<Transaction> transactions = new ArrayList<>();
	// transaction which i paid for
	// while return we will return this as well tx which involves us
	
	@OneToMany(mappedBy = "debtor")
    private Set<Debt> debtsAsDebtor = new HashSet<>(); 
	// Debts where the user is the debtor

	@OneToMany(mappedBy = "creditor")
    private Set<Debt> debtsAsCreditor = new HashSet<>(); 
	// Debts where the user is the creditor
	
	private BigDecimal amountOwed = BigDecimal.ZERO; 
	// Balance owed by the user
	
	
	
}
