package com.example.sustainability.api.data;

import javax.persistence.*;

import java.time.Instant;
import java.util.Date;

@Entity
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "worker_id")
    private User user;

    private final Date expiryDate;


    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = Date.from(Instant.now());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
}