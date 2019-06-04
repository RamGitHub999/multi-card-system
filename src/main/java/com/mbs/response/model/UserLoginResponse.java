package com.mbs.response.model;

import org.springframework.http.ResponseEntity;

public class UserLoginResponse {

	private String user_email;
	private String user_pwd;
	private String status;
	private ResponseEntity<Response> responseEntity;
	
	//Constructors
	public UserLoginResponse() {
		
	}
	public UserLoginResponse(String user_email, String user_pwd, String status, ResponseEntity<Response> responseEntity) {
		this.user_email = user_email;
		this.user_pwd = user_pwd;
		this.status = status;
		this.responseEntity = responseEntity;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ResponseEntity<Response> getResponseEntity() {
		return responseEntity;
	}
	public void setResponseEntity(ResponseEntity<Response> responseEntity) {
		this.responseEntity = responseEntity;
	}
	
	
	
}
