package com.game.service;

import java.util.List;

import com.game.domain.BoardVO;

public interface BoardService {

	public int insertBoard(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> selectBoards() throws Exception;				// 게시판 목록 가져오기
	
	public BoardVO selectBoard(int board_num) throws Exception;		// 글 내용 읽기
	
	public int addViews(int board_num) throws Exception;
	
}
