package com.apppassarourbano.AppPassaroUrbano.config.security;

import java.io.Serializable;
import java.util.List;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
    private final String refreshToken;

    public JwtResponse(String jwttoken, String refreshToken) {
        this.jwtToken = jwttoken;
        this.refreshToken = refreshToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
