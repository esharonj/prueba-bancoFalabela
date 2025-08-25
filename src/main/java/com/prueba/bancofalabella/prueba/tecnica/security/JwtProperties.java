package com.prueba.bancofalabella.prueba.tecnica.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {
    private String secret;
    private long expirationSeconds;


    public String getSecret() { return secret; }
    public void setSecret(String secret) { this.secret = secret; }
    public long getExpirationSeconds() { return expirationSeconds; }
    public void setExpirationSeconds(long expirationSeconds) { this.expirationSeconds = expirationSeconds; }
}
