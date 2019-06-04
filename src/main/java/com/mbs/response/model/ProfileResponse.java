package com.mbs.response.model;


import org.springframework.data.annotation.Id;
import org.springframework.http.ResponseEntity;

public class ProfileResponse {

	private String user_name;
	private String user_pwd;
	private String user_contact;
	private String user_email;
	private String user_proof;
	private String user_proof_type;
	private String status;
	private ResponseEntity<Response> responseEntity;

	// Constructors
	public ProfileResponse() {

	}

	public ProfileResponse(String user_name, String user_pwd, String user_contact, String user_email,
			String user_proof, String user_proof_type, String status,  ResponseEntity<Response> responseEntity) {
		this.user_name = user_name;
		this.user_pwd = user_pwd;
		this.user_contact = user_contact;
		this.user_email = user_email;
		this.user_proof_type = user_proof_type;
		this.user_proof = user_proof;
		this.status = status;
		this.responseEntity = responseEntity;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getUser_contact() {
		return user_contact;
	}

	public void setUser_contact(String user_contact) {
		this.user_contact = user_contact;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_proof() {
		return user_proof;
	}

	public void setUser_proof(String user_proof) {
		this.user_proof = user_proof;
	}

	
	public String getUser_proof_type() {
		return user_proof_type;
	}

	public void setUser_proof_type(String user_proof_type) {
		this.user_proof_type = user_proof_type;
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
