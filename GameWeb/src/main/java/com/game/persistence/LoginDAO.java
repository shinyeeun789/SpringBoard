package com.game.persistence;

import java.util.Map;

import com.game.domain.LoginVO;

public interface LoginDAO {
	
	public int IDCheck(String userID) throws Exception;
	
	public int insertUser(LoginVO loginVO) throws Exception;
	
	public Map<String, Object> naverConnectionCheck(Map<String, Object> apiJson);
	
	public LoginVO userNaverLoginPro(Map<String, Object> apiJson);
	
	public int insertNaverUser(LoginVO loginVO) throws Exception;

	public void updateKaKoLogin(LoginVO loginVO) throws Exception;
	
	public void insertKaKoLogin(LoginVO loginVO) throws Exception;
	
	public void updateLogout(LoginVO loginVO) throws Exception;
	
}
