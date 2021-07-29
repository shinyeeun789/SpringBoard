package com.game.persistence;

import java.util.Map;

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

<<<<<<< HEAD
	@Override
	public Map<String, Object> naverConnectionCheck(Map<String, Object> apiJson) {
		return session.selectOne(namespace+".naverConnectionCheck", apiJson);
	}

	@Override
	public Map<String, Object> userNaverLoginPro(Map<String, Object> apiJson) {
		return session.selectOne(namespace+".userNaverLoginPro", apiJson);
	}

	@Override
	public int insertNaverUser(LoginVO loginVO) throws Exception {
		
		System.out.println(loginVO.getNaver_login().length());
		return session.insert(namespace+".insertNaverUser", loginVO);
=======

	@Override
	public void updateKaKoLogin(LoginVO loginVO) throws Exception {
		session.update(namespace+".updateKaKoLogin", loginVO);
		
>>>>>>> e1d647ceb36e75aa13b17c85ad8146d8c3cc3344
	}
}
