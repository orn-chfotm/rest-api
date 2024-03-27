package com.spring.restapi.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenProvider {


    private final int expTime;

    private final Key secretKey;

    private Map<String, Object> header;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey,
                            @Value("${jwt.exp-time}") int expTime) {
        this.header = Map.of(
            "type", "jwt",
            "alg", "HS256"
        );
        byte[] KeyBytes = Decoders.BASE64.decode(secretKey);
        this.secretKey = Keys.hmacShaKeyFor(KeyBytes);
        this.expTime = expTime;
    }

    public Claims setClaims(String subject) {
        Date now = new Date();
        long expMilli = now.getTime() + expTime;

        return Jwts.claims()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date(expMilli));
    }

    public String createToken(Long memberId) {
        Claims claims = setClaims("access_token");
        claims.put("memberId", memberId);

        return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String refreshToken(Long memberId) {
        Claims claims = setClaims("refresh_token");
        claims.put("memberId", memberId);

        return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Token Expired");
            return false;
        } catch (JwtException e) {
            log.error("Token Error");
            return false;
        }
    }

}
