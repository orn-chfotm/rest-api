package com.spring.restapi.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;
import java.util.Date;


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
        System.out.println(new Date(newTimeInMillis));

        String jwt = Jwts.builder()
                .setHeaderParam("type", "jwt")
                .setIssuedAt(now)
                .setExpiration(now)
                .signWith(SignatureAlgorithm.HS256 ,secret)
                .compact();

        System.out.println(jwt);
    }
}