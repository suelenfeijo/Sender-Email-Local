package com.suelen.springmail.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Service
@AllArgsConstructor
public class EmailService implements EmailSender{

	/*leia JavaMailSender para entender mais sobre SMTP e o maildev */
	
	private final static Logger LOGGER = LoggerFactory
	            .getLogger(EmailService.class);
	
    private final JavaMailSender mailSender;

	@Override
	/*anotações em JavaMailSender.txt -> pasta relembrar*/
	@Async
	public void send(String to, String email) {
		
		/*entenda mais sobre JavaMailSender e MimeMessage na pasta relembrar do projeto*/
      try {
    	  MimeMessage mimeMessage = mailSender.createMimeMessage();
          MimeMessageHelper helper =
                  new MimeMessageHelper(mimeMessage, "utf-8");
          helper.setText(email, true);
          helper.setTo(to);
          helper.setSubject("Confirme seu email");
          helper.setFrom("suelenfeijobr@gmail.com");
          mailSender.send(mimeMessage);
	 } catch (MessagingException e) {
         LOGGER.error("falha ao enviar o email", e);
         throw new IllegalStateException("falha ao enviar email");
     }
      
	}
}
