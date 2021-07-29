package com.game.service;

import java.util.Map;

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

	@Override
	public Map<String, Object> naverConnectionCheck(Map<String, Object> apiJson) throws Exception {
		return dao.naverConnectionCheck(apiJson);
	}

	@Override
	public Map<String, Object> userNaverLoginPro(Map<String, Object> apiJson) throws Exception {
		return dao.userNaverLoginPro(apiJson);
	}

	@Override
	public int insertNaverUser(LoginVO loginVO) throws Exception {
		return dao.insertNaverUser(loginVO);
	}
}
