package com.game.service;

<<<<<<< HEAD
import java.util.Map;

=======
import com.fasterxml.jackson.databind.JsonNode;
>>>>>>> e1d647ceb36e75aa13b17c85ad8146d8c3cc3344
import com.game.domain.LoginVO;

public interface LoginService {
	
	public boolean IDCheck(String userID) throws Exception;
	
	public int insertUser(LoginVO loginVO) throws Exception;
	
<<<<<<< HEAD
	public Map<String, Object> naverConnectionCheck(Map<String, Object> apiJson) throws Exception;
	
	public Map<String, Object> userNaverLoginPro(Map<String, Object> apiJson) throws Exception;
	
	public int insertNaverUser(LoginVO loginVO) throws Exception;
=======
	public LoginVO kakaoLogin(JsonNode userInfo) throws Exception;
>>>>>>> e1d647ceb36e75aa13b17c85ad8146d8c3cc3344
}