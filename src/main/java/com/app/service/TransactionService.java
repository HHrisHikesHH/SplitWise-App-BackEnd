package com.app.service;

import java.util.List;

import com.app.DTO.ResponseDTO;
import com.app.DTO.TransactionDTO;
import com.app.entity.Group;
import com.app.entity.Transaction;

public interface TransactionService {

	List<TransactionDTO> getAllExpenses(Integer groupId);

	ResponseDTO addExpenses(TransactionDTO transactionDTO);

}
