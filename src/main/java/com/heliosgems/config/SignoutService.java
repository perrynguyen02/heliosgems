package com.heliosgems.config;

import com.heliosgems.repository.TokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignoutService implements LogoutHandler {
    @Autowired
    private TokenRepo tokenRepo;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHerder = request.getHeader("Authorization");
        String jwt;

        if (authHerder == null || !authHerder.startsWith("Bearer ")) {
            return;
        }
        jwt = authHerder.substring(7);
        var storedToken = tokenRepo.findByToken(jwt).orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            tokenRepo.save(storedToken);
            SecurityContextHolder.clearContext();
        }
    }
}
