package com.game.auth.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KakaoRestApi {
	private final static String K_CLIENT_ID = "db1cd9e8343b76d2a21deae2ce17abbd";
	private final static String K_REDIRECT_URI="http://localhost:8081/login/kakaoOauth.do";
	
	/** authorization Url get
	 * 
	 * @param session
	 * @return
	 */
	public String getAuthorizationUrl(HttpSession session) {
		
		String kakoUrl = "https://kauth.kako.com/oauth/authorize?"
				+"cliendt_id=" + K_CLIENT_ID + "&redirect_uri=" 
				+ K_REDIRECT_URI + "&response_type=code";
		return kakoUrl;
	}
	
	
	/** access�넗�겙 get
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
		postParams.add(new BasicNameValuePair("code", autorize_code)); 
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		JsonNode returnNode=null;
		
		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			final int reposeCode =response.getStatusLine().getStatusCode();
			
			//JSON �삎�깭 諛섑솚媛� 泥섎━
			
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
		
		final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
		
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
