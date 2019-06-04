package com.mbs.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mbs.request.model.Account;
import com.mbs.request.model.Profile;

public interface UserProfileRepository extends MongoRepository<Profile, String> {
	
	Profile findBy_id(ObjectId _id);
	

	@Query("{'user_email' : ?0}")
	Profile validateEmail(String userEmail);

	
	
}
