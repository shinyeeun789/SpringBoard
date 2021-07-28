package com.game.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.game.domain.LoginVO;

public interface LoginService {
	
	public boolean IDCheck(String userID) throws Exception;
	
	public int insertUser(LoginVO loginVO) throws Exception;

	public LoginVO kakaoSignUP(JsonNode userInfo) throws Exception;
}