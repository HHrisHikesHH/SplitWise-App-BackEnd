package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DTO.UserDTO;
import com.app.dao.UserDao;
import com.app.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO signin(UserDTO userDto) {
		User user = modelMapper.map(userDto, User.class);
		// ToDo
		// encode password, then store afterward
		userDao.save(user);
		return userDto;
	}

}
