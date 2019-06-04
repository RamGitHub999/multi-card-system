package com.mbs.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mbs.request.model.Account;

public interface AccountManagementRepository extends MongoRepository<Account, String>{
	
	Account findBycardId(String cid);
	
	@Query("{'user_email' : ?0}")
	List<Account> listByUserEmail(String useremail);
	
	@Query("{'user_cardBankName' : ?0}")
	Account searchByBankName(String searchByBankName);
}
