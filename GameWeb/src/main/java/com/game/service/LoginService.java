package com.game.service;

import com.game.domain.LoginVO;

public interface LoginService {
	
	public boolean IDCheck(String userID) throws Exception;
	
<<<<<<< HEAD
	//public void snsSingUp()
=======
	public int insertUser(LoginVO loginVO) throws Exception;
>>>>>>> d577ada632533c514c2fa8d7687391e8d64cce25
}