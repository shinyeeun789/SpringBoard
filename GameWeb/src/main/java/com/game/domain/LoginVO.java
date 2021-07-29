package com.game.domain;

import java.sql.Time;

public class LoginVO {
	private int user_num;
	private String user_id;
	private String user_name;
	private String password;
	private Time login_time;
	private String userName;
	private String userID;
	private String userPW;
	private String login_type;
	private String login_status;
	private String naver_login;
	private String sns_id;				//sns_login_info
	
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Time getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Time login_time) {
		this.login_time = login_time;
	}
	public String getLogin_type() {
		return login_type;
	}
	public void setLogin_type(String login_type) {
		this.login_type = login_type;
	}
	public String getLogin_status() {
		return login_status;
	}
	public void setLogin_status(String login_status) {
		this.login_status = login_status;
	}
	public String getNaver_login() {
		return naver_login;
	}
	public void setNaver_login(String naver_login) {
		this.naver_login = naver_login;
	}
	public String getSns_id() {
		return sns_id;
	}
	public void setSns_id(String sns_id) {
		this.sns_id = sns_id;
	}
}
