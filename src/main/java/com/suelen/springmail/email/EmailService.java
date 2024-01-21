package com.suelen.springmail.email;

import org.springframework.mail.javamail.JavaMailSender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class EmailService implements EmailSender{

	
    private final JavaMailSender mailSender;

	@Override
	public void send(String to, String email) {
		
	}

}
