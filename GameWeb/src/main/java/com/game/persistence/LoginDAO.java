package com.game.persistence;

import com.game.domain.LoginVO;

public interface LoginDAO {
	
	public int IDCheck(String userID) throws Exception;
	
	public int insertUser(LoginVO loginVO) throws Exception;

	public void updateKaKoLogin(LoginVO loginVO) throws Exception;
}
