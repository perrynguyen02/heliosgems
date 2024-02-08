package com.heliosgems.repository;

import com.heliosgems.model.entity.Token;
import com.heliosgems.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepo extends JpaRepository<Token, UUID> {
    Optional<Token> findByToken(String token);

    List<Token> findByUser(User user);
}
