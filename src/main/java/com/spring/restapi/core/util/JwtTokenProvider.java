package com.spring.restapi.core.util;

import com.spring.restapi.member.doamin.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {


    private final int accessExpTime;

    private final int refreshExpTime;

    private final Key secretKey;

    private Map<String, Object> header;

    private final UserDetailsService userDetailsService;

    /**
     * Constructor initializes the token provider
     */
    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey,
                            @Value("${jwt.access-exp-time}") int accessExpTime,
                            @Value("${jwt.refresh-exp-time}") int refreshExpTime,
                            UserDetailsService userDetailsService) {
        this.header = Map.of(
            "type", "jwt",
            "alg", "HS512"
        );
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        this.accessExpTime = accessExpTime;
        this.refreshExpTime = refreshExpTime;
        this.userDetailsService = userDetailsService;
    }

    /**
     * set Common Claims
     */
    public Claims setClaims(String subject, int expTime) {
        Date now = new Date();
        long expMilli = now.getTime() + expTime;

        return Jwts.claims()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date(expMilli));
    }

    /**
     * generate access token
     */
    public String generateAccessToken(Member member) {
        Claims claims = setClaims("access_token", accessExpTime);
        claims.put("memberId", member.getId());
        claims.put("memberEmail", member.getEmail());
        claims.put("role", member.getRole());

        return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * generate refresh token
     */
    public String generateRefreshToken(Member member) {
        Claims claims = setClaims("refresh_token", refreshExpTime);
        claims.put("memberId", member.getId());
        claims.put("memberEmail", member.getEmail());
        claims.put("role", member.getRole());

        return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Token validation
     *
     * @return true or false
     */
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

    /**
     * Get member id from token
     */
    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if(claims.get("memberId") == null || claims.get("role") == null) {
            throw new RuntimeException("Invalid token");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.get("memberId").toString());
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }



}
