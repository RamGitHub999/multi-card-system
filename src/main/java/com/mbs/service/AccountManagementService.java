package com.mbs.service;

import java.util.List;

import javax.validation.Valid;

import com.mbs.request.model.Account;
import com.mbs.response.model.AccountResponse;

public interface AccountManagementService {

	AccountResponse newAccount(Account account);

	List<Account> allAccountsOfUserService(String useremail);

	AccountResponse accountUpdationService(String user_bankName, @Valid Account account);

	AccountResponse accountRemovalService(String user_email, String user_bankName);
	
}
