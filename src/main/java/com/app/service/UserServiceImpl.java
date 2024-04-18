package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DTO.LogInDTO;
import com.app.DTO.SignInDTO;
import com.app.dao.UserDao;
import com.app.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

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

}
