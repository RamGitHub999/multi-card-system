package com.mbs.response.model;

import org.springframework.http.ResponseEntity;

public class AccountResponse {
	private String user_name;
	private String user_email;
	private String user_nameOnCard;
	private String user_cardNumber;
	private String user_cardBankName;
	private String user_cardExpiryDate;
	private String status;
	private ResponseEntity<Response> responseEntity;

	// Constructors
	public AccountResponse() {

	}

	public AccountResponse(String user_name, String user_email, String user_nameOnCard, String user_cardNumber,
			String user_cardBankName, String user_cardExpiryDate, String status, ResponseEntity<Response> responseEntity) {
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_nameOnCard = user_nameOnCard;
		this.user_cardNumber = user_cardNumber;
		this.user_cardBankName = user_cardBankName;
		this.user_cardExpiryDate = user_cardExpiryDate;
		this.status = status;
		this.responseEntity = responseEntity;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_nameOnCard() {
		return user_nameOnCard;
	}

	public void setUser_nameOnCard(String user_nameOnCard) {
		this.user_nameOnCard = user_nameOnCard;
	}

	public String getUser_cardNumber() {
		return user_cardNumber;
	}

	public void setUser_cardNumber(String user_cardNumber) {
		this.user_cardNumber = user_cardNumber;
	}

	public String getUser_cardBankName() {
		return user_cardBankName;
	}

	public void setUser_cardBankName(String user_cardBankName) {
		this.user_cardBankName = user_cardBankName;
	}

	public String getUser_cardExpiryDate() {
		return user_cardExpiryDate;
	}

	public void setUser_cardExpiryDate(String user_cardExpiryDate) {
		this.user_cardExpiryDate = user_cardExpiryDate;
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
