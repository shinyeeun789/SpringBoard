package com.game.login.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.tomcat.util.bcel.classfile.ElementValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;


@Controller
public class KakaoController  {

	private final static String K_CLIENT_ID = "db1cd9e8343b76d2a21deae2ce17abbd";
	private final static String K_REDIRECT_URI="http://localhost:8081/main.do";
	
	/** authorization Url get
	 * 
	 * @param session
	 * @return
	 */
	public String getAuthorizzationUrl(HttpSession session) {
		
		String kakoUrl = "https://kauth.kako.com/oauth/authorize?"
				+"cliendt_id=" + K_CLIENT_ID + "&redirect_uri=" 
				+ K_REDIRECT_URI + "&response_type=code";
		return null;
	}
	
	
	/** access토큰 get
	 * 
	 * @param autorize_code
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String getAccessToken(String autorize_code) throws ClientProtocolException, IOException {
		
		final String RequestUrl = "https://kauth.kakao.com/oauth/token";
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", K_CLIENT_ID)); //REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", K_REDIRECT_URI)); // redrect uri
		postParams.add(new BasicNameValuePair("code", autorize_code)); // 로그인 과정중 얻는 code 값
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		JsonNode returnNode=null;
		
		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			final int reposeCode =response.getStatusLine().getStatusCode();
			
			//JSON 형태 반환값 처리
			
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
			
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		
		return returnNode.get("access_token").toString();
	}
	
	public JsonNode getKakaoUserInfo(String autorize_code) throws ClientProtocolException, IOException {
		
		final String RequestUrl = "https://kapi.kakao.com/v1/user/me";
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		String accessToken = getAccessToken(autorize_code);
		
		post.addHeader("Authorization", "Bearer " + accessToken);
		
		JsonNode returnNode =null;
		
		try {
			
			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode();
			System.out.println("\nSending 'POST request to URL : " + RequestUrl);
			System.out.println("Response Code : " + responseCode);
			
			//Json 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
			
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return returnNode;
	}
}
