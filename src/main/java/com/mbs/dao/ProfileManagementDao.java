package com.mbs.dao;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mbs.repositories.UserLoginRepository;
import com.mbs.repositories.UserProfileRepository;
import com.mbs.request.model.Profile;
import com.mbs.request.model.UserLogin;
import com.mbs.response.model.ProfileResponse;
import com.mbs.response.model.Response;
import com.mbs.response.model.UserLoginResponse;
import com.mbs.service.ProfileManagementService;

@Service
public class ProfileManagementDao implements ProfileManagementService {

	@Autowired
	private UserProfileRepository profileRepository;

	@Autowired
	private UserLoginRepository userLoginRepository;

	@Override
	public ProfileResponse userSignUpService(Profile profile) {
		ProfileResponse profileResponse = new ProfileResponse();
		try {
			if (profileRepository.validateEmail(profile.getUser_email()) == null) {
				// Creating new Id for incoming profile
				// profile.set_id(ObjectId.get());
				// Populating ProfileResponse POJO Class
				profileResponse.setUser_contact(profile.getUser_contact());
				profileResponse.setUser_email(profile.getUser_email());
				profileResponse.setUser_pwd(profile.getUser_pwd());
				profileResponse.setUser_name(profile.getUser_name());
				profileResponse.setUser_proof(profile.getUser_proof());
				profileResponse.setUser_proof_type(profile.getUser_proof_type());
				profileResponse.setStatus("Success");
				// Saving user sent request profile pojo to MongoDB
				profileRepository.save(profile);
				ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
						new Response("Profile Created", 200, "Profile Creation Success"), HttpStatus.OK);
				profileResponse.setResponseEntity(responseEntity);

			} else {
				profileResponse.setUser_email(profile.getUser_email());
				ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
						new Response("Already Registered", 409, "Profile exists with the given email"),
						HttpStatus.CONFLICT);
				profileResponse.setResponseEntity(responseEntity);
				profileResponse.setStatus("Failure");
			}
		} catch (Exception e) {
			profileResponse.setStatus("Error: " + e.getMessage());
			ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
					new Response(e.getMessage(), 400, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
			profileResponse.setResponseEntity(responseEntity);
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return profileResponse;
	}

	@Override
	public Profile findBy_id(ObjectId id) {
		Profile profile = profileRepository.findBy_id(id);
		return profile;
	}

	@Override
	public Profile findByUserEmail(String user_email) {
		return profileRepository.validateEmail(user_email);
	}

	@Override
	public UserLoginResponse validateUser(UserLogin userLogin) {
		String userEmail = userLogin.getUser_email();
		String userPwd = userLogin.getUser_pwd();
		System.out.println("useremail in dao is: " + userEmail);
		System.out.println("userpwd in dao is: " + userPwd);

		UserLoginResponse userLoginResponse = new UserLoginResponse();

		try {
			if (userLoginRepository.validateEmail(userEmail) != null) {
				if (userLoginRepository.validatePwd(userPwd) != null) {
					System.out.println("User email and password correct");
					userLoginResponse.setUser_email(userEmail);
					userLoginResponse.setUser_pwd(userPwd);
					userLoginResponse.setStatus("Success");
					ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
							new Response("Login Success", 200, "Authentication Passed"), HttpStatus.OK);
					userLoginResponse.setResponseEntity(responseEntity);
				} else {
					userLoginResponse.setUser_email(userEmail);
					userLoginResponse.setUser_pwd(userPwd);
					userLoginResponse.setStatus("Failure");
					ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
							new Response("Wrong Password", 401, "Password not match"), HttpStatus.UNAUTHORIZED);
					userLoginResponse.setResponseEntity(responseEntity);
				}
			} else {
				userLoginResponse.setStatus("Failure");
				userLoginResponse.setUser_email(userEmail);
				userLoginResponse.setUser_pwd(userPwd);
				ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
						new Response("Invalid Email", 401, "Please register first"), HttpStatus.UNAUTHORIZED);
				userLoginResponse.setResponseEntity(responseEntity);
			}
		} catch (Exception e) {
			userLoginResponse.setStatus("Error: " + e.getMessage());
			userLoginResponse.setUser_email(userEmail);
			userLoginResponse.setUser_pwd(userPwd);
			ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
					new Response(e.getLocalizedMessage(), 401, e.getStackTrace().toString()), HttpStatus.UNAUTHORIZED);
			userLoginResponse.setResponseEntity(responseEntity);
		}
		return userLoginResponse;
	}

	// Profile Updating Space
	@Override
	public ProfileResponse updateProfileService(String user_email, @Valid Profile profile) {
		ProfileResponse profileResponse = new ProfileResponse();
		try {
			if (profileRepository.validateEmail(user_email) != null) {
				Profile foundProfileByEmail = profileRepository.validateEmail(user_email);

				// Before Update, set request body's id with existing id in db
				profile.set_id(foundProfileByEmail.get_id());
				
				profileResponse.setUser_email(profile.getUser_email());
				profileResponse.setUser_name(profile.getUser_name());
				profileResponse.setUser_proof(profile.getUser_proof());
				profileResponse.setUser_pwd(profile.getUser_pwd());
				profileResponse.setUser_proof_type(profile.getUser_proof_type());
				profileResponse.setUser_contact(profile.getUser_contact());
				profileResponse.setStatus("Success");
				
				profileRepository.save(profile);
				ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
						new Response("Updation Success", 200, "Updated successfully"), HttpStatus.OK);
				profileResponse.setResponseEntity(responseEntity);
			} else {
				ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
						new Response("Updation Failure", 401, "Updated Failed as no profile registered with the given email"), HttpStatus.UNAUTHORIZED);
				profileResponse.setResponseEntity(responseEntity);
				profileResponse.setUser_email(user_email);
				profileResponse.setStatus("Failure");
			}
		} catch (Exception e) {
			ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(
					new Response(e.getLocalizedMessage(), 401, e.getMessage()), HttpStatus.UNAUTHORIZED);
			profileResponse.setResponseEntity(responseEntity);
			profileResponse.setUser_email(user_email);
			profileResponse.setStatus("Failure");
		}
		return profileResponse;
	}

}
