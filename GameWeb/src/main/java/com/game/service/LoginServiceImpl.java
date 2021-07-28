package com.game.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.game.domain.LoginVO;
import com.game.persistence.LoginDAO;

@Service
public class LoginServiceImpl implements LoginService {

	@Inject
	private LoginDAO dao;
	
	@Override
	public boolean IDCheck(String userID) throws Exception {
		if(dao.IDCheck(userID) == 0)
			return true;
		else
			return false;
	}

	@Override
	public int insertUser(LoginVO loginVO) throws Exception {
		return dao.insertUser(loginVO);
	}
}
