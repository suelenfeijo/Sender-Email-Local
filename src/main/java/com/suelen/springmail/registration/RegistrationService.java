package com.suelen.springmail.registration;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suelen.springmail.appuser.AppUser;
import com.suelen.springmail.appuser.AppUserRole;
import com.suelen.springmail.appuser.AppUserService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class RegistrationService {


    private final AppUserService appUserService;
 //   private final EmailValidator emailValidator;
 //   private final ConfirmationTokenService confirmationTokenService;
  //  private final EmailSender emailSender;

    public String register(RegistrationRequest request) {
       return "works";
    }
}
