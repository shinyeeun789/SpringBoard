package com.game.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/error/*")
public class ErrorController {
	
	@RequestMapping(value = "/error404", method = RequestMethod.GET)
	public void error404(HttpServletRequest request) throws Exception {
		System.out.println("[/error/error404] 404 error 발생");
	}

}
