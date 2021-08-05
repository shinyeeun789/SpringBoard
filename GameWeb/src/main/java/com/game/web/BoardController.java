package com.game.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.domain.BoardVO;
import com.game.service.BoardService;

@RequestMapping("/board/*")

@Controller
public class BoardController {

	@Inject
	private BoardService boardService;

	@RequestMapping(value = "/showList.do", method = RequestMethod.GET)
	public String readBoard(Model model, HttpSession requset) throws Exception {

		model.addAttribute("list", boardService.selectBoards());
		return "/board/showList";
	}
	
	@RequestMapping(value="/createBoard.do", method = RequestMethod.GET)
	public String createBoardGET(Model model, HttpSession request) {
		
		return "/board/createBoard";
	}
	
	@RequestMapping(value="/createBoard.do", method = RequestMethod.POST)
	public String createBoardPOST(HttpSession request, BoardVO boardVO, Model model) throws Exception {
		
		int result = boardService.insertBoard(boardVO);
		
		return "redirect:/board/showList.do";
	}
	
	@RequestMapping(value="/readBoard.do", method = RequestMethod.GET)
	public String readBoard(HttpSession request, int board_num, Model model) throws Exception {
		
		boardService.addViews(board_num);									// 조회수 1회 올리기
		
		model.addAttribute("board", boardService.selectBoard(board_num));	
		return "/board/readBoard";
	}
}
