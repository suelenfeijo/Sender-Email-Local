package com.suelen.springmail.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

	//Data transference object (DTO) de AppUser
	 private final String firstName;
	    private final String lastName;
	    private final String email;
	    private final String password;
	    
}
