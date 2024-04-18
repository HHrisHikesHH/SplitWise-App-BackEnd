package com.app.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entity.Group;
import com.app.entity.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer>{

	List<Transaction> findByGroup(Group group);

	
	@Query(value = "SELECT members_involved_user_id FROM transaction_tbl_members_involved WHERE transaction_tx_id = :txId", nativeQuery = true)
	List<Integer> getMembers(Integer txId);



}
