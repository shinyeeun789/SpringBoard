package com.game.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.game.auth.web.KakaoRestApi;



@Controller
@RequestMapping("/login/*")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	private KakaoRestApi kakao_rest_api = new KakaoRestApi();
	
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public void login(Model model, HttpSession session) throws Exception {
		
		String KakaoUrl = kakao_rest_api.getAuthorizationUrl(session);
		//생성한 인증 URL을 View로 전달
		model.addAttribute("kakao_url", KakaoUrl);
		
		System.out.println("/login/login");
		
	}
	
	
	@RequestMapping(value = "/kakaoOauth.do")
	public String getKakaoSignIn(ModelMap model,@RequestParam("code") String code, HttpSession session) throws Exception {

	  JsonNode userInfo = kakao_rest_api.getKakaoUserInfo(code);

	  System.out.println(userInfo);

	  String id = userInfo.get("id").toString();
	  String email = userInfo.get("kaccount_email").toString();
	  String nickname = userInfo.get("properties").get("nickname").toString();

	  System.out.println(id + email);


	  model.addAttribute("k_userInfo", userInfo);
	  model.addAttribute("id", id);
	  model.addAttribute("email", email);
	  model.addAttribute("nickname", nickname);

	  return "/main.do";
	}
	
	
	@RequestMapping(value = "/signUp.do", method = RequestMethod.GET)
	public void signUp(Model model) throws Exception {
		System.out.println("/login/signUp");
	}
	
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST)
	public String idCheck(Model model, String userID) throws Exception {
		System.out.println(userID);
		return "/login/signUp.do";
	}
}
