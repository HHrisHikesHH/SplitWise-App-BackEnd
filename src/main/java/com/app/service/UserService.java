package com.app.service;

import com.app.DTO.LogInDTO;
import com.app.DTO.SignInDTO;
import com.app.entity.User;

public interface UserService {

	SignInDTO signin(SignInDTO userDto);

	boolean login(LogInDTO userDto);

}
