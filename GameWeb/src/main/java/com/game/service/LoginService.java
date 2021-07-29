package com.game.service;

import java.util.Map;

import com.game.domain.LoginVO;

public interface LoginService {
	
	public boolean IDCheck(String userID) throws Exception;
	
	public int insertUser(LoginVO loginVO) throws Exception;
	
	public Map<String, Object> naverConnectionCheck(Map<String, Object> apiJson) throws Exception;
	
	public Map<String, Object> userNaverLoginPro(Map<String, Object> apiJson) throws Exception;
	
	public int insertNaverUser(LoginVO loginVO) throws Exception;
}