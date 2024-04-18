package com.app.service;

import java.util.List;
import java.util.Set;

import com.app.DTO.DebtDTO;
import com.app.DTO.LogInDTO;
import com.app.DTO.SignInDTO;
import com.app.entity.Debt;
import com.app.entity.User;

public interface UserService {

	SignInDTO signin(SignInDTO userDto);

	boolean login(LogInDTO userDto);

	List<List<DebtDTO>> getMyDebts(Integer userId);

	List<DebtDTO> getMyNetDebts(Integer userId);

}
