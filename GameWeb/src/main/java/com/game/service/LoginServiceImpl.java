package com.game.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
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
	public LoginVO kakaoLogin(JsonNode userInfo) throws Exception {
		LoginVO loginVO = new LoginVO();
		
		try {
			
			loginVO.setUserID(userInfo.get("id").toString());
			loginVO.setLogin_status("login");
			loginVO.setUserName(userInfo.get("properties").get("nickname").toString());
			loginVO.setLogin_type("KAKAO");
			
			dao.updateKaKoLogin(loginVO);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginVO;
	}
	
	
}
