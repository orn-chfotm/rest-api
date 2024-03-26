package com.spring.restapi.util;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${jwt.exp-time}")
    private int expTime;

    @Value("${jwt.secret}")
    private Key secret;

    private Map<String, Object> header;

    public JwtTokenProvider() {
        this.header = Map.of(
            "type", "jwt",
            "alg", "HS256"
        );
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
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String refreshToken(Long memberId) {
        Claims claims = setClaims("refresh_token");
        claims.put("memberId", memberId);

        return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean checkTimeOut(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secret)
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
