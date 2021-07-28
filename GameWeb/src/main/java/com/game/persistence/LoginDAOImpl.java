package com.game.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.game.domain.LoginVO;

@Repository
public class LoginDAOImpl implements LoginDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.game.mapper.loginMapper";

	@Override
	public int IDCheck(String userID) throws Exception {
		return session.selectOne(namespace+".IDCheck", userID);
	}

	@Override
	public int insertUser(LoginVO loginVO) throws Exception {
		return session.insert(namespace+".insertUser", loginVO);
	}


	@Override
	public void updateKaKoLogin(LoginVO loginVO) throws Exception {
		session.update(namespace+".updateKaKoLogin", loginVO);
		
	}
}
