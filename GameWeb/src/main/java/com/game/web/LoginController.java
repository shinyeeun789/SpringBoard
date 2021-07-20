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
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(Model model) throws Exception {
		logger.info("/login/login");
		
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public void signUp(Model model) throws Exception {
		
		// return "/login/signUp";
	}
}
