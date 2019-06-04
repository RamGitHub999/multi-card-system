package com.mbs.controller;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbs.request.model.Profile;
import com.mbs.request.model.UserLogin;
import com.mbs.response.model.ProfileResponse;
import com.mbs.response.model.UserLoginResponse;
import com.mbs.service.ProfileManagementService;

@RestController
@RequestMapping("/MultiBankingSystem/ProfileManagement/")
public class ProfileManagenentController {

	@Autowired
	private ProfileManagementService profileService;

	@GetMapping("/TestApplication")
	public String testApplication() {
		return "MBS is up and Working!!";
	}

	@GetMapping("/Profiles/{id}")
	public Profile getProfileById(@PathVariable("id") ObjectId id) {
		return profileService.findBy_id(id);
	}

	@GetMapping("/Profiles/{useremail}")
	public Profile getProfileByEmail(@PathVariable("useremail") String user_email) {
		return profileService.findByUserEmail(user_email);
	}

	@PostMapping("/ProfileRegistration")
	public ProfileResponse userSignup(@Valid @RequestBody Profile profile) {
		return profileService.userSignUpService(profile);

	}

	@PostMapping("/ProfileLogin")
	public UserLoginResponse userLogin(@RequestBody UserLogin userLogin) {
		return profileService.validateUser(userLogin);
	}

	@PostMapping("/ProfileUpdation")
	public ProfileResponse updateProfile(@RequestParam("useremail") String user_email,
			@Valid @RequestBody Profile profile) {
		return profileService.updateProfileService(user_email, profile);
	}
}
