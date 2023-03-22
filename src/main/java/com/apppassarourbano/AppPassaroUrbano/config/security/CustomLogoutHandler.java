package com.apppassarourbano.AppPassaroUrbano.config.security;

import com.apppassarourbano.AppPassaroUrbano.config.security.TokenRefreshRequest;
import com.apppassarourbano.AppPassaroUrbano.service.RefreshTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;

public class CustomLogoutHandler implements LogoutHandler {
    @Autowired
    private RefreshTokenService refreshTokenService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            String body = s.hasNext() ? s.next() : "";
            ObjectMapper mapper = new ObjectMapper();
            TokenRefreshRequest tokenRefreshRequest = mapper.readValue(body, TokenRefreshRequest.class);

            refreshTokenService.deleteByToken(tokenRefreshRequest.getRefreshToken());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
