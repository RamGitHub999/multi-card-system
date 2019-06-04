package com.mbs.controller;

import java.util.List;

import javax.validation.Valid;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mbs.request.model.Account;
import com.mbs.response.model.AccountResponse;
import com.mbs.service.AccountManagementService;

@RestController
@RequestMapping("/MultiBankingSystem/AccountsManagement")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountsManagementController {
	
	@Autowired
	private AccountManagementService accountManagement;
	
	@PostMapping("/AccountCreation")
	public AccountResponse accountCreation(@Valid @RequestBody Account account) {
		return accountManagement.newAccount(account);
	}

	//@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/Accounts/AccountsList")
	public List<Account> allAccountsOfUser(@RequestParam("useremail") String useremail){
		return accountManagement.allAccountsOfUserService(useremail);
	}
	
	@PutMapping("/Accounts/AccountsUpdation")
	public AccountResponse accountUpdation(@RequestParam("userbankname") String userbankname, @Valid @RequestBody Account account) {
		return accountManagement.accountUpdationService(userbankname, account);
	}
	
	@DeleteMapping("/Accounts/AccountsRemoval")
	public AccountResponse accountRemoval(@RequestParam("useremail") String user_email, @RequestParam("userbankname") String user_bankName) {
		return accountManagement.accountRemovalService(user_email, user_bankName);
	}
}
