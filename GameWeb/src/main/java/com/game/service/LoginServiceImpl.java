package com.game.service;

import java.util.Map;

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
	public Map<String, Object> naverConnectionCheck(Map<String, Object> apiJson) throws Exception {
		return dao.naverConnectionCheck(apiJson);
	}

	@Override
	public LoginVO userNaverLoginPro(Map<String, Object> apiJson) throws Exception {
		return dao.userNaverLoginPro(apiJson);
	}

	@Override
	public int insertNaverUser(LoginVO loginVO) throws Exception {
		return dao.insertNaverUser(loginVO);
	}

	@Override
	public LoginVO kakaoLogin(JsonNode userInfo) throws Exception {
		LoginVO loginVO = new LoginVO();
		
		try {
			
			loginVO.setUser_id(userInfo.get("id").toString());
			loginVO.setLogin_status("login");
			loginVO.setUser_name(userInfo.get("properties").get("nickname").toString());
			loginVO.setLogin_type("KAKAO");
			
			dao.updateKaKoLogin(loginVO);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginVO;
	}
}
