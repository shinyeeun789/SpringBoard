package com.game.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.game.domain.BoardVO;
import com.game.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO boardDAO;

	@Override
	public int insertBoard(BoardVO boardVO) throws Exception {
		
		String content = boardVO.getContent().replace("\r\n", "<br>");
		boardVO.setContent(content);
		
		return boardDAO.insertBoard(boardVO);
	}

}
