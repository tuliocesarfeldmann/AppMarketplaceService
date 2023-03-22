package com.apppassarourbano.AppPassaroUrbano.config.security;

public class TokenRefreshResponse {
    private String jwtToken;
    private String refreshToken;

    public TokenRefreshResponse(String jwtToken, String refreshToken) {
        this.jwtToken = jwtToken;
        this.refreshToken = refreshToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
