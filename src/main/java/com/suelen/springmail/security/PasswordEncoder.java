package com.suelen.springmail.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {

	
	
	
	/*A anotação @Bean indica que este método é um método de 
	 * configuração que retorna um objeto do tipo BCryptPasswordEncoder.
	 *  O Spring Security vai detectar isso e vai usar o objeto retornado 
	 *  como o PasswordEncoder padrão para sua aplicação.*/
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
