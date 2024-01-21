package com.suelen.springmail.email;

import org.springframework.mail.javamail.JavaMailSender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
@AllArgsConstructor
public class EmailService implements EmailSender{

	
	private final static Logger LOGGER = LoggerFactory
	            .getLogger(EmailService.class);
	
    private final JavaMailSender mailSender;

	@Override
	public void send(String to, String email) {
		
	}

}
