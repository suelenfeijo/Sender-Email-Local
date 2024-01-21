package com.suelen.springmail.registration;

import org.springframework.stereotype.Service;

import com.suelen.springmail.appuser.AppUser;
import com.suelen.springmail.appuser.AppUserRole;
import com.suelen.springmail.appuser.AppUserService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
@AllArgsConstructor
public class RegistrationService {

	private final EmailValidator emailValidator;
	private final AppUserService appUserService;

	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		if (!isValidEmail) {
			throw new IllegalArgumentException("o email não é válido");
		}

		String token = appUserService.signUpUser
			  (new AppUser(request.getFirstName(),
		        request.getLastName(),
				request.getEmail(),
				request.getPassword(),
				AppUserRole.USER

		)
	);
		return token;
	}
	
	
	
	
	
	
	
	
}
