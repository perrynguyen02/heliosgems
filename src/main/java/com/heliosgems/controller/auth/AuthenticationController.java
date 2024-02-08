package com.heliosgems.controller.auth;

import com.heliosgems.config.LogoutService;
import com.heliosgems.model.dto.UserDto;
import com.heliosgems.model.entity.User;
import com.heliosgems.model.request.AuthenticationRequest;
import com.heliosgems.model.response.AuthenticationResponse;
import com.heliosgems.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private LogoutService logoutService;

    @PostMapping("/register")
    public AuthenticationResponse signupAcocunt(@RequestBody UserDto userDto) {
        if(authenticationService.isUsernameExists(userDto.getUsername())) {
            return AuthenticationResponse.builder()
                    .description("User already exists")
                    .build();
        }
        return authenticationService.register(userDto);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse httpServletResponse) {
        return authenticationService.authenticate(authenticationRequest, httpServletResponse);
    }

    @PostMapping("/logout")
    public void logoutToken(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logoutService.logout(request, response, authentication);
    }
}
