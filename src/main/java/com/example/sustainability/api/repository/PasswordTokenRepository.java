package com.example.sustainability.api.repository;

import com.example.sustainability.api.data.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);
}
