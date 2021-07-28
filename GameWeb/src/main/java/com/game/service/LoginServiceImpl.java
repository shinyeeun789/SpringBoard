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
	public LoginVO kakaoSignUP(JsonNode userInfo) throws Exception { 
	
		LoginVO loginVO = new LoginVO();
		
		try {
			
			loginVO.setUserID(userInfo.get("id").toString());
			loginVO.setUserName(userInfo.get("properties").get("nickname").toString());
			loginVO.setLogin_type("KAKAO");
			loginVO.setLogin_status("login");
			
			dao.insertKaKoUser(loginVO);
//			dao.insertKaKoUser(loginVO);
//			dao.insertKaKoUser(loginVO);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginVO;
	}
	
	
}
