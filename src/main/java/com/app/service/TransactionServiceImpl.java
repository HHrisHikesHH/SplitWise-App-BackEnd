package com.app.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DTO.ResponseDTO;
import com.app.DTO.TransactionDTO;
import com.app.dao.DebtDao;
import com.app.dao.GroupDao;
import com.app.dao.TransactionDao;
import com.app.dao.UserDao;
import com.app.entity.Debt;
import com.app.entity.Group;
import com.app.entity.Transaction;
import com.app.entity.User;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ResponseDTO responseDTO;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DebtDao debtDao;

	@Override
	public List<TransactionDTO> getAllExpenses(Integer groupId) {
		List<TransactionDTO> allExpense = new ArrayList<>();

		Optional<Group> groupOptional = groupDao.findById(groupId);

		if (groupOptional.isPresent()) {
			Group group = groupOptional.get();
			List<Transaction> allExpenseTx = transactionDao.findByGroup(group);

			// Mapping the fetched transactions to DTOs
			allExpenseTx.forEach(t -> {
				TransactionDTO tDTO = mapper.map(t, TransactionDTO.class);
				tDTO.setPaidById(t.getPaidBy().getUserId());
				tDTO.setGroupId(t.getGroup().getGroupId());
				tDTO.setGroupName(t.getGroup().getGroupName());

				tDTO.setMembersInvolvedId(
						t.getMembersInvolved().stream().map(User::getUserId).collect(Collectors.toList()));

				allExpense.add(tDTO);
			});
		}

		return allExpense;
	}

	@Override
	public ResponseDTO addExpenses(TransactionDTO transactionDTO) {
		String message = "Expense added!";
		boolean status = true;

		Transaction tx = mapper.map(transactionDTO, Transaction.class);

		try {
			Group group = groupDao.findById(transactionDTO.getGroupId()).get();
			tx.setGroup(group);
			try {
				User paidBy = userDao.findById(transactionDTO.getPaidById()).get();
				tx.setPaidBy(paidBy);
				List<User> membersInvolved = new ArrayList<>();

				// ID to User conversion and null checking
				for (Integer m : transactionDTO.getMembersInvolvedId()) {
					try {
						User user = userDao.findById(m).get();
						membersInvolved.add(user); // error
					} catch (NoSuchElementException e) {
						message = String.format("Member with ID %d not available!", m);
						status = false;
						break;
					}
				}
				//
				tx.setMembersInvolved(membersInvolved); // for new grp add grp manually

				transactionDao.save(tx);

				doDebitLogic(paidBy, membersInvolved
//					        .peek(m -> m.getMyGroups().add(group.get())) // Modify each User instance
						, tx.getAmount());
			} catch (NoSuchElementException e) {
				message = "payer not found ";
				status = false;
			}
		} catch (NoSuchElementException e) {
			message = "Group not found ";
			status = false;
		}

		responseDTO.setMessage(message);
		responseDTO.setSuccess(status);
		return responseDTO;
	}

	private void doDebitLogic(User paidBy, List<User> membersInvolved, BigDecimal amount) {
		BigDecimal amountPerPerson = amount.divide(BigDecimal.valueOf(membersInvolved.size()), 2, RoundingMode.HALF_UP);

		updateCreditorDebitorDebt(paidBy, membersInvolved, amountPerPerson);

	}

	// only to add/update records of debit and credit net debt not calculated here
//	private void updateCreditorDebitorDebt(User creditor, List<User> membersInvolved, BigDecimal amount) {
//		Set<Debt> asCreditor = creditor.getDebtsAsCreditor();
//
//		asCreditor.forEach(d -> {
//			membersInvolved.forEach(u -> {
//				// finding out if these user owe us money already or not
//				if (!d.getDebtor().equals(u)) {
//					Debt newDebt = new Debt();
//					newDebt.setCreditor(creditor); // setting ourSelves as creditor
//					newDebt.setDebtor(u); // setting this user as debitor
//					newDebt.setAmount(amount);
//
//					debtDao.save(newDebt);
//
//					asCreditor.add(newDebt); // adding to our credit set
//
//					u.getDebtsAsDebtor().add(newDebt); // adding to users debit set
//				} else {
//					// update existing amount in ours Set where we are creditor
//					d.setAmount(d.getAmount().add(amount));
//
//					// update existing amount in users Set where we are creditor
//					u.getDebtsAsDebtor().add(d);
//				}
//			});
//		});
//	}

	private void updateCreditorDebitorDebt(User creditor, List<User> membersInvolved, BigDecimal amount) {
	    for (User debtor : membersInvolved) {
	        boolean debtExists = false;
	        for (Debt debt : creditor.getDebtsAsCreditor()) {
	            if (debt.getDebtor().equals(debtor)) {
	                // Update existing debt amount
	                debt.setAmount(debt.getAmount().add(amount));
	                debtExists = true;
	                break;
	            }
	        }
	        if (!debtExists) {
	            // Create a new debt
	            Debt newDebt = new Debt();
	            newDebt.setCreditor(creditor);
	            newDebt.setDebtor(debtor);
	            newDebt.setAmount(amount);
	            debtDao.save(newDebt);
	        }
	    }
	}

}
