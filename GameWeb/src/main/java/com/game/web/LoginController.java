package com.game.web;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.auth.web.KakaoRestApi;
import com.game.domain.LoginVO;
import com.game.domain.NaverLoginBO;
import com.game.service.LoginService;
import com.github.scribejava.core.model.OAuth2AccessToken;



@Controller
@RequestMapping("/login/*")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Inject
	private LoginService service;
	
	@Inject
	BCryptPasswordEncoder pwdEncoder;							// 비밀번호 암호화
	
	private NaverLoginBO naverLoginbo = new NaverLoginBO();
	private KakaoRestApi kakao_rest_api = new KakaoRestApi();
	
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public void login(Model model, HttpSession session) throws Exception {
		
		String KakaoUrl = kakao_rest_api.getAuthorizationUrl(session);
		model.addAttribute("kakao_url", KakaoUrl);
		
		String naverUrl = naverLoginbo.getAuthorizationUrl(session);
		model.addAttribute("naverUrl", naverUrl);
	}
	
	
	@RequestMapping(value = "/kakaoOauth.do")
	public String getKakaoSignIn(ModelMap model,@RequestParam("code") String code, HttpSession session) throws Exception {

		//JsonNode accessToketn = kakao_rest_api.getAccessToken(code);

		JsonNode userInfo = kakao_rest_api.getKakaoUserInfo(code);
				
		System.out.println(userInfo);

		String id = userInfo.get("id").toString();
		//String email = userInfo.get("kaccount_email").toString();
		String nickname = userInfo.get("properties").get("nickname").toString();

		System.out.println(nickname);


		model.addAttribute("k_userInfo", userInfo);
		model.addAttribute("id", id);
		//model.addAttribute("email", email);
	  	model.addAttribute("nickname", nickname);

	  	return "login/loginifo";
	}
	
	// SignUp GET
	@RequestMapping(value = "/signUp.do", method = RequestMethod.GET)
	public void signUpGET(Model model) throws Exception {
		logger.info("get signUp");
	}
	
	// SignUp POST
	@RequestMapping(value = "/signUp.do", method = RequestMethod.POST)
	public String signUpPOST(Map<String, Object> modelMap, LoginVO userInfo, HttpServletRequest request) throws Exception {
		logger.info("post signUp");
		
		// 비밀번호 암호화하여 userInfo에 넣어주기
		String pwd = pwdEncoder.encode(userInfo.getPassword());
		userInfo.setPassword(pwd);
		
		service.insertUser(userInfo);
		
		// System.out.println(pwdEncoder.matches("asdf1234", userInfo.getUserPW()));
		return "redirect:/login/login.do";
	}
	
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST)
	public void idCheck(HttpServletRequest request, String user_id, HttpServletResponse response) throws Exception {
		JSONObject jsonObject = new JSONObject();
		boolean result = service.IDCheck(user_id);
		jsonObject.put("result", result);
		try {
			response.getWriter().print(jsonObject);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 닉네임 설정 화면 GET
	@RequestMapping(value="/setUserName.do")
	public void userName(HttpServletRequest request, String email, String password) {
		System.out.println("/login/setUserName.do");
	}
	
	// 닉네임 설정 화면 POST
	@RequestMapping(value="/setUserName.do", method=RequestMethod.POST)
	public String userName(HttpServletRequest request, LoginVO loginVO, HttpSession session) throws Exception {
		
		service.insertNaverUser(loginVO);

		return "redirect:/main.do";
	}
	
	// 네이버 로그인 callback 컨트롤러
	@RequestMapping(value="/naverCallback.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String naverCallback(RedirectAttributes rttr, @RequestParam Map<String, Object> paramMap, @RequestParam String code, @RequestParam String state, HttpSession session) throws SQLException, Exception {
		
		System.out.println("paramMap:" + paramMap);
		
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginbo.getAccessToken(session, code, state);
		
		// 로그인 사용자 정보를 읽어옴
		String apiResult = naverLoginbo.getUserProfile(oauthToken);
		System.out.println("apiResult => " + apiResult);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> apiJson = (Map<String, Object>)objectMapper.readValue(apiResult, Map.class).get("response");
		Map<String, Object> naverConnectionCheck = service.naverConnectionCheck(apiJson);
		
		if(naverConnectionCheck == null) {						// 가입이 되어 있지 않아 회원가입해야 하는 상태
			rttr.addFlashAttribute("email", apiJson.get("email"));
			rttr.addFlashAttribute("naverLogin", apiJson.get("id"));
			return "redirect:/login/setUserName.do";
		} else {												// 네이버로 가입이 된 상태
			Map<String, Object> loginCheck = service.userNaverLoginPro(apiJson);
			session.setAttribute("userInfo", loginCheck);
		}
		return "redirect:/main.do";
	}
}
