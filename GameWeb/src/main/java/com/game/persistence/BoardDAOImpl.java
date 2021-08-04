package com.game.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.game.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.game.mapper.boardMapper";


	@Override
	public int insertBoard(BoardVO boardVO) throws Exception {
		
		return session.insert(namespace+".insertBoard", boardVO);
	}
	
}
