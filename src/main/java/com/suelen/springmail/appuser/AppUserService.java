package com.suelen.springmail.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.suelen.springmail.registration.token.ConfirmationToken;
import com.suelen.springmail.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found :";

    
    private final AppUserRepository appUserRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                        		/*aqui USER_NOT_FOUND_MSG -> usei (%s)
                        		 * , chamo o objeto string
                        		 * e chamo o método .format , esse método,
                        		 * vai buscar o coringa (%s) em USER_NOT_FOUND_MSG
                        		 * e vai colocar o valor de (email) onde
                        		 * (%s) está marcando */
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    
    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            // TODO verifica se email já existe
            // TODO se o email não encontrado, manda o email.

            throw new IllegalStateException("e-mail já recebido");
        }
    
       String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
        
        
        /*Método que cria token  */
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(1),
                appUser
        );
        /*Salvando o token enviado para confirmação*/
        confirmationTokenService.saveConfirmationToken(
        confirmationToken);
        
        /*Enviando token*/
        return token;
    }



    

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}