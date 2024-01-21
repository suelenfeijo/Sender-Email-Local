package com.suelen.springmail.registration;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path =  "api/v1/registration")
public class RegistrationController {
	
	
	
	 private final RegistrationService registrationService = null;
	 
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}



}
