package com.heliosgems.services;

import com.heliosgems.config.jwt.JwtService;
import com.heliosgems.model.dto.UserDto;
import com.heliosgems.model.entity.User;
import com.heliosgems.model.request.AuthenticationRequest;
import com.heliosgems.model.response.AuthenticationResponse;
import com.heliosgems.repository.UserRepo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public List<User> getAllAccountAccount() {
        return userRepo.findAll();
    }

    public void signupAccount(UserDto userDto) {
        var account = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .role("Customer")
                .build();
        userRepo.save(account);
    }

    public String signinAccount(UserDto userDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        var user = userRepo.findByUsername(userDto.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        String jwtToken = jwtService.generateToken(user);

        return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest, HttpServletResponse httpServletResponse) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        } catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException("Incorrect username or password");
        }
        User user = userRepo.findByUsername(authenticationRequest.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        String jwtToken = jwtService.generateToken(user);

        httpServletResponse.addHeader(HEADER_STRING, TOKEN_PREFIX + jwtToken);

        return AuthenticationResponse.builder()

                .build();
    }

    public void signoutAccount() {

    }

//    private void saveToken(User user, String jwtToken) {
//        Token token = Token.builder()
//                .user(user)
//                .token(jwtToken)
//                .expired(false)
//                .build();
//        tokenRepo.save(token);
//    }
}
