package com.mbs.service;

import javax.validation.Valid;

import org.bson.types.ObjectId;

import com.mbs.request.model.Profile;
import com.mbs.request.model.UserLogin;
import com.mbs.response.model.ProfileResponse;
import com.mbs.response.model.UserLoginResponse;


public interface ProfileManagementService {
	public ProfileResponse userSignUpService(Profile profile);

	public Profile findBy_id(ObjectId id);

	public Profile findByUserEmail(String user_email); 
	
	public UserLoginResponse validateUser(UserLogin userLogin);

	public ProfileResponse updateProfileService(String user_email, @Valid Profile profile);
}
