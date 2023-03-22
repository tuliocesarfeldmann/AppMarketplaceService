package com.apppassarourbano.AppPassaroUrbano.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import com.apppassarourbano.AppPassaroUrbano.repository.refreshToken.RefreshTokenRepository;
import com.apppassarourbano.AppPassaroUrbano.repository.user.UserRepository;
import com.apppassarourbano.AppPassaroUrbano.exception.TokenRefreshException;
import com.apppassarourbano.AppPassaroUrbano.model.entity.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RefreshTokenService {
    @Value("${jwt.refreshExpirationDateInMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        LocalDateTime now = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
        if (token.getExpiryDate().isBefore(now)) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByToken(String token) {
        return refreshTokenRepository.deleteByToken(token);
    }

    @Transactional
    public void delete(RefreshToken token) {
        refreshTokenRepository.delete(token);
    }
}