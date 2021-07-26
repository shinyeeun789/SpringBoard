package com.game.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public void login(Model model) throws Exception {
		System.out.println("/login/login");
		
	}
	
	@RequestMapping(value = "/signUp.do", method = RequestMethod.GET)
	public void signUp(Model model) throws Exception {
		System.out.println("/login/signUp");
	}
	
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST)
	public String idCheck(Model model, String userID) throws Exception {
		System.out.println(userID);
		return "/login/signUp";
	}
}
