package com.suelen.springmail.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailService implements EmailSender{

	
	private final static Logger LOGGER = LoggerFactory
	            .getLogger(EmailService.class);
	
    private final JavaMailSender mailSender;

	@Override
	@Async
	public void send(String to, String email) {
		
		
      try {
    	  MimeMessage mimeMessage = mailSender.createMimeMessage();
          MimeMessageHelper helper =
                  new MimeMessageHelper(mimeMessage, "utf-8");
          helper.setText(email, true);
          helper.setTo(to);
          helper.setSubject("Confirme seu email");
          helper.setFrom("suelenfeijobr@gmail.com");
	 } catch (MessagingException e) {
         LOGGER.error("falha ao enviar o email", e);
         throw new IllegalStateException("falha ao enviar email");
     }
      
	}
}
