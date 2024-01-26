package com.heliosgems.repository;

import com.heliosgems.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, String> {
    Optional<Account> findByUsername(String username);
}
