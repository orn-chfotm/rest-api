package com.spring.restapi.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.*;

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

        Key secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        Date now = new Date();
        System.out.println(now);
        long newTimeInMillis = now.getTime() + expTime;
        long expMilli = now.getTime() + 10000000;

        Claims claims = Jwts.claims()
                .setSubject("access-token")
                .setIssuedAt(now)
                .setExpiration(new Date(expMilli));
        claims.put("memberId", "1");

        String jwt = Jwts.builder()
                .setHeader(Map.of(
                        "typ", "jwt",
                        "alg", "HS512"
                ))
                .setHeaderParam("type", "jwt")
                .setIssuedAt(now)
                .setClaims(claims)
                .setExpiration(new Date(expMilli))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();

        Jwt<JwsHeader, Claims> resultClaims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt);

        Claims resuleClaims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        assert resuleClaims.getSubject().equals("access-token");
        System.out.println(jwt);
        System.out.println(resuleClaims.getSubject());
        System.out.println(resuleClaims.get("memberId"));

        System.out.println(resultClaims.getBody());
        System.out.println(resultClaims.getHeader());

    }

}