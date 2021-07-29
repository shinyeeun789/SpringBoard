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
	   BCryptPasswordEncoder pwdEncoder;      // 암호화 기능
	   
	   
	   
	   @RequestMapping(value = "/login.do", method = RequestMethod.GET)
	   public void login(Model model, HttpSession session) throws Exception {
		   
		  KakaoRestApi kakao_rest_api = new KakaoRestApi();
	      
		  String KakaoUrl = kakao_rest_api.getAuthorizationUrl(session);
		  
		  model.addAttribute("kakao_url", KakaoUrl);
		  System.out.println(KakaoUrl);
		  
		  System.out.println("/login/login");
	      
	   }
	   
	   
	   @RequestMapping(value = "/kakaoOauth.do")
	   public String getKakaoSignIn(ModelMap model,@RequestParam("code") String code, HttpSession session) throws Exception {
		
		   KakaoRestApi kakao_rest_api = new KakaoRestApi();
		   
		   //사용자 정보
			JsonNode userInfo = kakao_rest_api.getKakaoUserInfo(code);
			//access 토큰
			JsonNode node = kakao_rest_api.getAccessToken(code);
			
			LoginVO loginVO = new LoginVO();
			
			// 카카오 로그인 및 회원가입
			try {
				loginVO = service.kakaoLogin(userInfo);
				session.setAttribute("loginCheck ", true);
				session.setAttribute("userInfo", loginVO);
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return "redirect:/main.do";
	   }
	   
	   // 회원가입 GET
	   @RequestMapping(value = "/signUp.do", method = RequestMethod.GET)
	   public void signUpGET(Model model) throws Exception {
	        logger.info("get signUp");
	   }
	   
	   // 회원가입 POST
	   @RequestMapping(value = "/signUp.do", method = RequestMethod.POST)
	   public String signUpPOST(Map<String, Object> modelMap, LoginVO userInfo, HttpServletRequest request) throws Exception {
	        logger.info("post signUp");
	  
			// 비밀번호 암호화하여 userInfo에 넣어주기
			String pwd = pwdEncoder.encode(userInfo.getUserPW());
			userInfo.setUserPW(pwd);
			service.insertUser(userInfo);
				  
			// System.out.println(pwdEncoder.matches("asdf1234", userInfo.getUserPW()));
			return "redirect:/login/login.do";
	   }
	   
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
	   
	   
	   @RequestMapping(value="/logout.do", method = RequestMethod.GET)
	   public String logout(Model model, HttpSession session) throws Exception {
		   //
		   LoginVO loginVO = (LoginVO) session.getAttribute("userInfo");
		   
		   if(loginVO.getLogin_type() == "KAKAO") {
			   //카카오 restApi 객체 선어
			   KakaoRestApi kakao_rest_api = new KakaoRestApi();
			   
			   // 노드에 로그아웃한 결과값을 담아줌, 매개변수는 세션에있는 token을 가져옴
			   JsonNode node = kakao_rest_api.Logout(session.getAttribute("token").toString());
			   
			   System.out.println("로그아웃 후 반환되는 아이디 :"+ node.get("id"));
		   }
		   
		   // 세션 무효화
		   session.invalidate();
		   
		   return "redirect:/main.do";
	   }
	   
	   
	   
}