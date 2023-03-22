package com.apppassarourbano.AppPassaroUrbano.repository.refreshToken;

import java.util.Optional;

import com.apppassarourbano.AppPassaroUrbano.model.entity.RefreshToken;
import com.apppassarourbano.AppPassaroUrbano.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByToken(String token);

    Optional<RefreshToken> findByUser(User user);
}
