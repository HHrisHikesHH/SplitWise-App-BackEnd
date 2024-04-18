package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DTO.DebtDTO;
import com.app.DTO.LogInDTO;
import com.app.DTO.SignInDTO;
import com.app.dao.DebtDao;
import com.app.dao.UserDao;
import com.app.entity.Debt;
import com.app.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DebtDao debtDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public SignInDTO signin(SignInDTO userDto) {
		User user = modelMapper.map(userDto, User.class);
		// ToDo
		// encode password, then store afterward
		userDao.save(user);

		// genearte JWT and add into cookie
		return userDto;
	}

	@Override
	public boolean login(LogInDTO userDto) {
		User user = userDao.findByEmail(userDto.getEmail());
		if (user == null)
			return false;
		else
			return user.getPassword().equals(userDto.getPassword());
	}

	@Override
	public List<List<DebtDTO>> getMyDebts(Integer userId) {
		List<List<DebtDTO>> res = new ArrayList<>();
		User user = userDao.findById(userId).get();
		
		List<Debt> crd = user.getDebtsAsCreditor();
		List<Debt> deb = user.getDebtsAsDebtor();
		
		List<DebtDTO> crdList = new ArrayList<>();
		res.add(crdList);
		for(Debt d : crd) {
			DebtDTO debtDto = new DebtDTO();
			
			debtDto.setCreditorId(userId); // cause its credit list of user
			debtDto.setDebtorId(d.getDebtor().getUserId());
			
			if(debtDto.getCreditorId().equals(debtDto.getDebtorId())) continue;
			
			debtDto.setAmount(d.getAmount());
			debtDto.setCreditorName(d.getCreditor().getFirstName());
			debtDto.setDebtorName(d.getDebtor().getFirstName());
			
			crdList.add(debtDto);
		}
		
		List<DebtDTO> debList = new ArrayList<>();
		res.add(debList);
		for(Debt d : deb) {
			DebtDTO debtDto = new DebtDTO();
			
			debtDto.setDebtorId(userId); // cause its debt list of user
			debtDto.setCreditorId(d.getCreditor().getUserId());
			
			if(debtDto.getCreditorId().equals(debtDto.getDebtorId())) continue;
			
			debtDto.setAmount(d.getAmount());
			debtDto.setCreditorName(d.getCreditor().getFirstName());
			debtDto.setDebtorName(d.getDebtor().getFirstName());
			
			debList.add(debtDto);
		}	
		return res;
	}

	@Override
	public List<DebtDTO> getMyNetDebts(Integer userId) {
		List<List<DebtDTO>> crdDeb = getMyDebts(userId);
		
		List<DebtDTO> crd = crdDeb.get(0);
		List<DebtDTO> deb = crdDeb.get(1);
		
		List<DebtDTO> res = new ArrayList<>();
		
		for(DebtDTO c : crd) {
			int creditorIdCrd = c.getCreditorId();
			int debtorIdCrd = c.getDebtorId();
			
			if(creditorIdCrd == debtorIdCrd) continue;
			
			for(DebtDTO d : deb) {
				int creditorIdDeb = d.getCreditorId();
				int debtorIdDeb = d.getDebtorId();
				
				if(creditorIdDeb == debtorIdDeb) continue;
				
				if(creditorIdCrd == debtorIdDeb && creditorIdDeb == debtorIdCrd) {
					DebtDTO temp;
					if(c.getAmount().compareTo(d.getAmount()) <= 0) {
						temp = modelMapper.map(d, DebtDTO.class);
						temp.setAmount(c.getAmount().subtract(d.getAmount()).abs());
					}else {
						temp = modelMapper.map(c, DebtDTO.class);
						temp.setAmount(c.getAmount().subtract(d.getAmount()).abs());
					}
					
					res.add(temp);
				}
			}
		}
		
		return res;
	}

}
