package com.app.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entity.Debt;
import com.app.entity.User;

@Repository
public interface DebtDao extends JpaRepository<Debt, Integer>{

	Optional<Debt> findByCreditorAndDebtor(User creditor, User debitor);

	@Query(value = "SELECT * FROM debts WHERE creditor_id = ?1", nativeQuery = true)
	List<Debt> findByCreditorId(Integer userId);

	@Query(value = "SELECT * FROM debts WHERE debtor_id = ?1", nativeQuery = true)
	List<Debt> findByDebtorId(Integer userId);
	
//	List<Debt> findByCreditor(User userId);

//	List<Debt> findByDebtor(User user);

}
