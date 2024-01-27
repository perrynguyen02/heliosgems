package com.heliosgems.repository;

import com.heliosgems.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepo extends JpaRepository<Token, UUID> {
    //   timf kiếm tất cả các token của user đó chưa hết hạn và chuyển nó thành hết hạn

    Optional<Token> findByToken(String token);

}
