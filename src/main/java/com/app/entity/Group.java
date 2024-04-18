package com.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "group_tbl")
public class Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer groupId;
	
	private String groupName;
	
	@ManyToMany
	@JoinTable(
	    name = "user_group",
	    joinColumns = @JoinColumn(name = "group_id"),
	    inverseJoinColumns = @JoinColumn(name = "user_id")
	)
    private Set<User> members = new HashSet<>(); 
	
	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>(); 
	// Transactions associated with the group

}
