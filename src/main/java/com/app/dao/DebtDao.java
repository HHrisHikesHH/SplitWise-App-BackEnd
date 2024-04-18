package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Debt;
import com.app.entity.User;

@Repository
public interface DebtDao extends JpaRepository<Debt, Integer>{

	Optional<Debt> findByCreditorAndDebtor(User creditor, User debitor);

}
