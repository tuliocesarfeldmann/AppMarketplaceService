package com.apppassarourbano.AppPassaroUrbano.controller;

import com.apppassarourbano.AppPassaroUrbano.config.JwtTokenUtil;
import com.apppassarourbano.AppPassaroUrbano.config.security.*;
import com.apppassarourbano.AppPassaroUrbano.exception.TokenRefreshException;
import com.apppassarourbano.AppPassaroUrbano.model.entity.User;
import com.apppassarourbano.AppPassaroUrbano.service.JwtUserDetailsService;
import com.apppassarourbano.AppPassaroUrbano.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(name = "auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        final CustomUser userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token, refreshToken));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(token -> {
                    CustomUser userDetails = userDetailsService.loadUserByUsername(token.getUser().getUsername());
                    String newToken = jwtTokenUtil.generateToken(userDetails);
                    String newRefreshToken = jwtTokenUtil.generateRefreshToken(userDetails);

                    return ResponseEntity.ok(new TokenRefreshResponse(newToken, newRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user){
        return ResponseEntity.ok(userDetailsService.save(user));
    }

}
