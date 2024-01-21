package com.suelen.springmail.registration;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Service
@Getter
@Setter
@AllArgsConstructor
public class RegistrationService {



    public String register(RegistrationRequest request) {
       return "works";
    }
}
