package com.game.login.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

@Controller
public class KakaoController {

	private final static String K_CLIENT_ID = "db1cd9e8343b76d2a21deae2ce17abbd";
	private final static String K_REDIRECT_URI="http://localhost:8081/";
	
	public String getAuthorizzationUrl(HttpSession session) {
		
		String kakoUrl = "https://kauth.kako.com/oauth/authorize?"
				+"cliendt_id=" + K_CLIENT_ID + "&redirect_uri=" 
				+ K_REDIRECT_URI + "&response_type=code";
		return null;
	}
	
	public String getAccessToken(String autorize_code) {
		
		final String RequestUrl = "https://kauth.kakao.com/oauth/token";
		//final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		return null;
	}
}
