package com.spring.restapi.util;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;
import java.util.Date;
import java.util.Map;


@DisplayName("Jwt Test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JwtTokenProviderTest {

    static int expTime = 864000000;

    static String secret =  "a2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbXRva2FyaW10b2thcmltdG9rYXJpbQ";

    @Test
    @DisplayName("jwt")
    void setJwt() {
        Date now = new Date();
        long newTimeInMillis = now.getTime() + expTime;
        long expMilli = now.getTime() + expTime;

        Claims claims = Jwts.claims()
                .setSubject("access-token")
                .setIssuedAt(now)
                .setExpiration(new Date(expMilli));
        claims.put("memberId", "1");


        String jwt = Jwts.builder()
                .setHeader(Map.of(
                        "type", "jwt",
                        "alg", "HS256"
                ))
                .setHeaderParam("type", "jwt")
                .setIssuedAt(now)
                .setClaims(claims)
                .setExpiration(new Date(newTimeInMillis))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        Jwt resultClaims = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(jwt);

        System.out.println(resultClaims.getBody());
        System.out.println(resultClaims.getHeader());

    }

}