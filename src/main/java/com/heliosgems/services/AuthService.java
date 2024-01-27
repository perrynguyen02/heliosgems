package com.heliosgems.services;

import com.heliosgems.config.jwt.JwtService;
import com.heliosgems.model.dto.AccountDto;
import com.heliosgems.model.entity.Account;
import com.heliosgems.model.entity.Token;
import com.heliosgems.repository.AccountRepo;
import com.heliosgems.repository.TokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private TokenRepo tokenRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public List<Account> getAllAccountAccount() {
        return accountRepo.findAll();
    }

    public void signupAccount(AccountDto accountDto) {
        var account = Account.builder()
                .username(accountDto.getUsername())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .firstname(accountDto.getFirstname())
                .lastname(accountDto.getLastname())
                .email(accountDto.getEmail())
                .phoneNumber(accountDto.getPhoneNumber())
                .role("User")
                .build();
        accountRepo.save(account);
    }

    public String signinAccount(AccountDto accountDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountDto.getUsername(), accountDto.getPassword()));
        var user = accountRepo.findByUsername(accountDto.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        String jwtToken = jwtService.generateToken(user);

        return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        User user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserToken(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void signoutAccount() {

    }

    private void saveToken(Account account, String jwtToken) {
        Token token = Token.builder()
                .account(account)
                .token(jwtToken)
                .expired(false)
                .build();
        tokenRepo.save(token);
    }
}
