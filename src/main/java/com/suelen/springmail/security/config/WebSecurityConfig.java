package com.suelen.springmail.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.suelen.springmail.appuser.AppUserService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	
	
	  private final AppUserService appUserService;
	  private final BCryptPasswordEncoder bCryptPasswordEncoder;

	   

	/*O método configure com parâmetro HttpSecurity é usado para definir 
	 * as configurações de segurança HTTP para um aplicativo Spring 
	 * Security. Ele permite especificar diversas configurações 
	 * relacionadas à segurança, como quais URLs exigem autenticação, 
	 * quais mecanismos de autenticação usar e como lidar com falhas 
	 * de autenticação. Ao substituir esse método em uma subclasse de 
	 * WebSecurityConfigurerAdapter, você pode personalizar as configurações
	 *  de segurança para seu aplicativo específico.*/
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
		
		/*Esta linha desativa a proteção CSRF (Cross-Site Request Forgery)
		 *  do Spring Security. CSRF é um tipo de ataque malicioso no qual
		 *   um site é enganado para executar ações na conta de um usuário
		 *    sem o conhecimento do usuário. Desativar o CSRF pode ser 
		 *    perigoso, pois reduz a segurança do aplicativo. No entanto,
		 *     se você está usando uma abordagem diferente para garantir 
		 *     a segurança do CSRF, pode ser necessário desativar o CSRF.*/
		.csrf().disable()
		
		
		/*Esta linha configura o controle de acesso ao 
		 * recurso no Spring Security. A configuração 
		 * é iniciada usando o método authorizeRequests()*/
        .authorizeRequests()
        
        /*O método antMatchers() especifica um padrão
         *  de URL para o qual as regras de segurança
         *   serão aplicadas. Neste caso, as regras 
         *   serão aplicadas a todas as URLs que
         *    correspondem ao padrão /api/v* /registration/**. 
         *    O * no padrão indica que qualquer valor pode ser usado.*/
            .antMatchers("/api/v*/registration/**")
            
         /*O método permitAll() indica que todas as
          *  solicitações que correspondem ao padrão 
          *  de URL especificado devem ser permitidas.
          *   Neste caso, isso significa que todas as 
          *   solicitações para a URL /api/v* /registration/**
          *   serão permitidas.*/  
            .permitAll()
            
        /*O método anyRequest() indica que 
         * as regras de segurança que se seguem
         *  se aplicarão a qualquer solicitação 
         *  que não corresponda a nenhum padrão 
         *  especificado anteriormente.*/
        .anyRequest()
        
        /*O método authenticated() indica que as solicitações
         *  que correspondem ao padrão especificado devem 
         *  ser autenticadas. O método and() indica que esta
         *   configuração é a última da sequência atual.*/
        .authenticated().and()
        
        
        /* formLogin define a estratégia de autenticação 
         * do formulário. O método formLogin() configura 
         * o Spring Security para usar um formulário de 
         * login HTML para autenticar os usuários.*/
        .formLogin();
	}
	
	
	
	
	
	
	/*AuthenticationManagerBuilder é usada para configurar o
	 *  mecanismo de autenticação para um aplicativo. Ele permite
	 *   que você registre diferentes beans AuthenticationProvider
	 *    que serão usados para autenticar usuários.*/
	  @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  
		  
		  
		  /*authenticationProvider é uma instância da classe 
		   *  AuthenticationManagerBuilder. Este método é usado para 
		   *  registrar um bean AuthenticationProvider com o construtor.*/
	        auth.authenticationProvider(daoAuthenticationProvider());
	    }
	
	  
	  
	  /* responsável por criar e configurar um Bean DaoAuthenticationProvider
	   *  que será usado para autenticar os usuários do aplicativo usando o 
	   *  UserDetailsService e o PasswordEncoder especificados.
	   *  
	   *  DaoAuthenticationProvider é uma implementação concreta da 
	   *  interface AuthenticationProvider fornecida pelo Spring Security. 
	   *  Ele é usado para autenticar usuários com base nos detalhes do 
	   *  usuário armazenados em uma fonte de dados (como um banco de dados).*/
	  @Bean
	  public DaoAuthenticationProvider daoAuthenticationProvider() {
		  
	        DaoAuthenticationProvider provider =
	        		
	        		
	        /*é responsável por autenticar os usuários. Ele utiliza um 
	         * UserDetailsService para obter informações do usuário,
	         *  como nome de usuário, senha e autoridades (permissões),
	         *   e um PasswordEncoder para codificar e verificar senhas.*/
	                new DaoAuthenticationProvider();
	        
	        
	        /*define o codificador de senha como o 
	         * bCryptPasswordEncoder. O bCryptPasswordEncoder
	         *  é uma implementação popular do algoritmo de 
	         *  codificação de senha bcrypt.*/
	        provider.setPasswordEncoder(bCryptPasswordEncoder);
	        
	        /*define o UserDetailsService que será usado para
	         *  obter informações do usuário. Neste caso, o 
	         *  UserDetailsService é o appUserService.*/
	        provider.setUserDetailsService(appUserService);
	        
	        return provider;
	    }
}
