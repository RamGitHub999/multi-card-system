package com.mbs.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mbs.request.model.Profile;

public interface UserLoginRepository extends MongoRepository<Profile, String> {

	
	@Query("{'user_email' : ?0}")
	Profile validateEmail(String userEmail);
	
	@Query("{'user_pwd' : ?0}")
	Profile validatePwd(String userPwd);
}
