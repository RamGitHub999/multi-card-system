package com.mbs.dao;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mbs.repositories.AccountManagementRepository;
import com.mbs.repositories.UserProfileRepository;
import com.mbs.request.model.Account;
import com.mbs.request.model.Profile;
import com.mbs.response.model.AccountResponse;
import com.mbs.response.model.Response;
import com.mbs.service.AccountManagementService;

@Service
public class AccountManagementDao implements AccountManagementService {

	@Autowired
	private AccountManagementRepository accountManagementRepository;
	@Autowired
	private UserProfileRepository userProfileRepository;

	// Adding account card details (creation)
	@Override
	public AccountResponse newAccount(Account account) {
		AccountResponse accountResponse = new AccountResponse();
		try {
			if (!(userProfileRepository.validateEmail(account.getUser_email()).getUser_email().isEmpty())) {
				accountResponse.setUser_cardBankName(account.getUser_cardBankName());
				accountResponse.setUser_nameOnCard(account.getUser_nameOnCard());
				accountResponse.setUser_cardExpiryDate(account.getUser_cardExpiryDate());
				accountResponse.setUser_cardNumber(account.getUser_cardNumber());
				accountResponse.setUser_name(account.getUser_name());
				accountResponse.setUser_email(account.getUser_email());
				accountResponse.setStatus("Success");
				accountManagementRepository.save(account);

				ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
						new Response("Success", 200, "New Card Added successfully"), HttpStatus.OK);
				accountResponse.setResponseEntity(responseEntity);
			} else {
				accountResponse.setUser_cardBankName(account.getUser_cardBankName());
				accountResponse.setUser_nameOnCard(account.getUser_nameOnCard());
				accountResponse.setUser_cardExpiryDate(account.getUser_cardExpiryDate());
				accountResponse.setUser_cardNumber(account.getUser_cardNumber());
				accountResponse.setUser_name(account.getUser_name());
				accountResponse.setUser_email(account.getUser_email());
				accountResponse.setStatus("Error: Not a registered email");
				ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
						new Response("Failure", 204, "Not a registered email"), HttpStatus.NO_CONTENT);
				accountResponse.setResponseEntity(responseEntity);
			}
		} catch (Exception e) {
			accountResponse.setUser_cardBankName(account.getUser_cardBankName());
			accountResponse.setUser_nameOnCard(account.getUser_nameOnCard());
			accountResponse.setUser_cardExpiryDate(account.getUser_cardExpiryDate());
			accountResponse.setUser_cardNumber(account.getUser_cardNumber());
			accountResponse.setUser_name(account.getUser_name());
			accountResponse.setUser_email(account.getUser_email());
			accountResponse.setStatus("Failure");

			ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
					new Response(e.getLocalizedMessage(), 401, e.getMessage()), HttpStatus.UNAUTHORIZED);
			accountResponse.setResponseEntity(responseEntity);
		}
		return accountResponse;
	}

	@Override
	public List<Account> allAccountsOfUserService(String useremail) {
		return accountManagementRepository.listByUserEmail(useremail);
	}

	// Card Updating Space
	@Override
	public AccountResponse accountUpdationService(String user_bankName, @Valid Account account) {
		AccountResponse accountResponse = new AccountResponse();
		try {
			if (!(userProfileRepository.validateEmail(account.getUser_email()).getUser_email().isEmpty())
					&& accountManagementRepository.listByUserEmail(account.getUser_email()).size() != 0) {
				List<Account> foundAccountsByEmail = accountManagementRepository
						.listByUserEmail(account.getUser_email());
				for (Account eachAccount : foundAccountsByEmail) {
					if (eachAccount.getUser_cardBankName().equals(user_bankName)) {
						System.out.println(eachAccount.getCardId());

						// ****** Imp- Before updating, Set Requestbody's id with existing _id in
						// DB******
						account.setCardId(eachAccount.getCardId());

						accountResponse.setStatus("Success");
						accountResponse.setUser_cardBankName(account.getUser_cardBankName());
						accountResponse.setUser_cardNumber(account.getUser_cardNumber());
						accountResponse.setUser_nameOnCard(account.getUser_nameOnCard());
						accountResponse.setUser_cardExpiryDate(account.getUser_cardExpiryDate());
						accountResponse.setUser_email(account.getUser_email());
						accountResponse.setUser_name(account.getUser_name());
						ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
								new Response("Updation Success", 200, "Updated successfully"), HttpStatus.OK);
						accountResponse.setResponseEntity(responseEntity);
						accountManagementRepository.save(account);
						break;

					} else {
						accountResponse.setStatus("Failure");
						ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
								new Response("Bank Name Not Found", 204, "No card added with the given bank name"),
								HttpStatus.NO_CONTENT);
						accountResponse.setResponseEntity(responseEntity);
						accountResponse.setUser_cardBankName(user_bankName);
					}
				}
			} else {
				System.out.println("wrong email");
				ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
						new Response("Email Not Found", 204, "Not a registered email"), HttpStatus.NO_CONTENT);
				accountResponse.setResponseEntity(responseEntity);
				accountResponse.setUser_email(account.getUser_email());
			}
		} catch (Exception e) {
			ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
					new Response(e.getLocalizedMessage(), 401, e.getMessage()), HttpStatus.UNAUTHORIZED);
			accountResponse.setResponseEntity(responseEntity);
		}

		return accountResponse;
	}

	// Card Removal Space
	@Override
	public AccountResponse accountRemovalService(String user_email, String user_bankName) {
		AccountResponse accountResponse = new AccountResponse();
		try {
			if (accountManagementRepository.listByUserEmail(user_email).size() != 0) {
				List<Account> foundAccountByEmail = accountManagementRepository.listByUserEmail(user_email);
				for (Account eachAccount : foundAccountByEmail) {
					if (eachAccount.getUser_cardBankName().equals(user_bankName)) {
						accountManagementRepository.delete(eachAccount);
						ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
								new Response("Success", 200, "Card Details Removed"), HttpStatus.OK);
						accountResponse.setResponseEntity(responseEntity);
						accountResponse.setUser_email(user_email);
						accountResponse.setUser_cardBankName(user_bankName);
					} else {
						ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
								new Response("Bank Name Not Found", 204, "No card added with that bank name"),
								HttpStatus.NO_CONTENT);
						accountResponse.setResponseEntity(responseEntity);
						accountResponse.setUser_cardBankName(user_bankName);
					}
				}
			} else {
				ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
						new Response("Email Not Found", 204, "Not a registered email"), HttpStatus.NO_CONTENT);
				accountResponse.setResponseEntity(responseEntity);
				accountResponse.setUser_email(user_email);
			}
		} catch (Exception e) {
			ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
					new Response(e.getLocalizedMessage(), 304, e.getMessage()), HttpStatus.NOT_MODIFIED);
			accountResponse.setResponseEntity(responseEntity);
			accountResponse.setStatus("Failed");
		}
		return accountResponse;
	}

}
