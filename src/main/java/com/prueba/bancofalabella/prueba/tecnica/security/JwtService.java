package com.prueba.bancofalabella.prueba.tecnica.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;


@Service
public class JwtService {


    private final JwtProperties props;


    public JwtService(JwtProperties props) {
        this.props = props;
    }


    public String generateToken(String subject, Map<String, Object> claims) {
        SecretKey key = Keys.hmacShaKeyFor(props.getSecret().getBytes(StandardCharsets.UTF_8));
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(props.getExpirationSeconds())))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
