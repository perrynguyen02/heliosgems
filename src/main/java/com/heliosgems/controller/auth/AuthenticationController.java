package com.heliosgems.controller.auth;

import com.heliosgems.model.dto.AccountDto;
import com.heliosgems.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public void signupAcocunt(@RequestBody AccountDto accountDto) {
        authenticationService.signupAccount(accountDto);
    }
}
