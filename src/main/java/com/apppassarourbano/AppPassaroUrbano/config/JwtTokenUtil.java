package com.apppassarourbano.AppPassaroUrbano.config;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.apppassarourbano.AppPassaroUrbano.repository.refreshToken.RefreshTokenRepository;
import com.apppassarourbano.AppPassaroUrbano.repository.user.UserRepository;
import com.apppassarourbano.AppPassaroUrbano.config.security.CustomUser;
import com.apppassarourbano.AppPassaroUrbano.model.entity.RefreshToken;
import com.apppassarourbano.AppPassaroUrbano.model.entity.User;
import com.apppassarourbano.AppPassaroUrbano.service.JwtUserDetailsService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    @Value("${jwt.refreshExpirationDateInMs}")
    private int refreshExpirationDateInMs;

    @Value("${jwt.secret}")
    private String secret;


    //recupera nome de usuário do token jwt
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //recupera a data de expiração do token jwt
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //para recuperar qualquer informação do token precisaremos da chave secreta
    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        } catch (ExpiredJwtException expiredEx) {
            throw new ExpiredJwtException(null, null, "JWT Token expired");
        }
    }

    //verifica se o token expirou
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //gerar token para o usuário
    public String generateToken(CustomUser userDetails) {
        Map<String, Object> claims = new HashMap<>();

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return doGenerateToken(claims, userDetails, roles);
    }

    public String generateRefreshToken(CustomUser userDetails) {

        return doGenerateRefreshToken(userDetails.getUser());
    }

    //ao criar o token -
    //1. Defina declarações do token, como Emissor, Expiração, Assunto e o ID
    //2. Assine o JWT usando o algoritmo HS512 e a chave secreta.
    //3. De acordo com JWS Compact Serialization (https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    // compactação do JWT em uma string segura para URL
    private String doGenerateToken(Map<String, Object> claims, CustomUser user, List<String> roles) {
        return Jwts.builder().setClaims(claims).claim("user_id", user.getId()).claim("roles", roles)
                .setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String doGenerateRefreshToken(User user) {
        RefreshToken refreshToken;

        Optional<RefreshToken> existingRefreshToken = refreshTokenRepository.findByUser(user);

        if(existingRefreshToken.isPresent()){
            existingRefreshToken.get().setToken(UUID.randomUUID().toString());
            refreshToken = existingRefreshToken.get();
        } else {
            refreshToken = new RefreshToken();
            refreshToken.setUser(user);
            refreshToken.setExpiryDate(Instant.ofEpochMilli(System.currentTimeMillis() + refreshExpirationDateInMs).atZone(ZoneId.systemDefault()).toLocalDateTime());
            refreshToken.setToken(UUID.randomUUID().toString());
        }

        refreshToken = refreshTokenRepository.save(refreshToken);

        return refreshToken.getToken();
    }

    // valida o token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
