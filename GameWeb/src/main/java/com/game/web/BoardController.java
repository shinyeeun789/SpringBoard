package com.game.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/board/*")

@Controller
public class BoardController {

	@RequestMapping(value = "/createBoard.do", method = RequestMethod.GET)
	public String createBoard(Model model, HttpSession requset) {
		
		return "/board/createBoard";
	}
	
	@RequestMapping(value="/writeBoard.do")
	public String writeBoard(Model model, HttpSession request) {
		
		return "/board/writeBoard";
	}
}
