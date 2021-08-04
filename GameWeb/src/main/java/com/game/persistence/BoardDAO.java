package com.game.persistence;

import com.game.domain.BoardVO;

public interface BoardDAO {
	
	public int insertBoard(BoardVO boardVO) throws Exception;
	
}
