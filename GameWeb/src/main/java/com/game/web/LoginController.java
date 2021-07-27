package com.game.web;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.game.auth.web.KakaoRestApi;
import com.game.domain.LoginVO;
import com.game.service.LoginService;



@Controller
@RequestMapping("/login/*")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Inject
	private LoginService service;
	
	@Inject
	BCryptPasswordEncoder pwdEncoder;		// 암호화 기능
	
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
	
	// 회원가입 GET
	@RequestMapping(value = "/signUp.do", method = RequestMethod.GET)
	public void signUpGET(Model model) throws Exception {
		logger.info("get signUp");
	}
	
	// 회원가입 POST
	@RequestMapping(value = "/signUp.do", method = RequestMethod.POST)
	public String signUpPOST(Map<String, Object> modelMap, LoginVO userInfo) throws Exception {
		logger.info("post signUp");
		
		// 비밀번호 암호화하여 userInfo에 넣어주기
		String pwd = pwdEncoder.encode(userInfo.getUserPW());
		userInfo.setUserPW(pwd);
		
		
		// System.out.println(pwdEncoder.matches("asdf1234", userInfo.getUserPW()));
		return "/login/signUp.do";
	}
	
	// ID 중복 확인
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST)
	public void idCheck(HttpServletRequest request, String userID, HttpServletResponse response) throws Exception {
		JSONObject jsonObject = new JSONObject();
		boolean result = service.IDCheck(userID);
		jsonObject.put("result", result);
		try {
			response.getWriter().print(jsonObject);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
