package com.game.persistence;

import java.util.List;

import com.game.domain.BoardVO;

public interface BoardDAO {
	
	public int insertBoard(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> selectBoards() throws Exception;
	
	public BoardVO selectBoard(int board_num) throws Exception;
	
	public int addViews(int board_num) throws Exception;
	
}
