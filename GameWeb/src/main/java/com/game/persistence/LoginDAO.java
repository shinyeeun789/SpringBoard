package com.game.persistence;

import java.util.Map;

import com.game.domain.LoginVO;

public interface LoginDAO {
	
	public int IDCheck(String userID) throws Exception;
	
	public int insertUser(LoginVO loginVO) throws Exception;
<<<<<<< HEAD
	
	public Map<String, Object> naverConnectionCheck(Map<String, Object> apiJson);
	
	public Map<String, Object> userNaverLoginPro(Map<String, Object> apiJson);
	
	public int insertNaverUser(LoginVO loginVO) throws Exception;
=======

	public void updateKaKoLogin(LoginVO loginVO) throws Exception;
>>>>>>> e1d647ceb36e75aa13b17c85ad8146d8c3cc3344
}
