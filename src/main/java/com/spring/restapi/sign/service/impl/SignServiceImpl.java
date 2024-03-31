package com.spring.restapi.sign.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.restapi.core.exception.NotFoundDataException;
import com.spring.restapi.core.exception.TokenExpiredException;
import com.spring.restapi.core.util.JwtTokenProvider;
import com.spring.restapi.member.repository.MemberRepository;
import com.spring.restapi.sign.dto.request.SignRequest;
import com.spring.restapi.sign.dto.request.TokenRequest;
import com.spring.restapi.sign.dto.response.TokenResponse;
import com.spring.restapi.sign.service.SignService;
import com.spring.restapi.member.doamin.Member;
import com.spring.restapi.member.doamin.QMember;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignServiceImpl implements SignService {

    private  final MemberRepository memberRepository;

    private final JPAQueryFactory jpaQueryFactory;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenResponse login(SignRequest signRequest) {
        QMember qMember = QMember.member;

        Member member = jpaQueryFactory
                .selectFrom(qMember)
                .where(qMember.email.eq(signRequest.getEmail()))
                .fetchOne();

        if(member == null || !member.getPassword().equals(signRequest.getPassword())) {
            throw new RuntimeException("회원 정보가 일치하지 않습니다.");
        }

        /*
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                member.getId(),
                member.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        */

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(member))
                .refreshToken(jwtTokenProvider.generateRefreshToken(member))
                .build();
    }

    @Override
    public TokenResponse accessToken(TokenRequest tokenRequest) {
        return TokenResponse.builder()
                .accessToken(tokenRequest.getAccessToken())
                .refreshToken(tokenRequest.getRefreshToken())
                .useToken(jwtTokenProvider.validateToken(tokenRequest.getAccessToken()))
                .build();
    }

    @Override
    public TokenResponse refreshToken(TokenRequest tokenRequest) {

        if(jwtTokenProvider.validateToken(tokenRequest.getRefreshToken())) {

            Claims claims = jwtTokenProvider.parseClaims(tokenRequest.getRefreshToken());

            Member member = memberRepository.findById(Long.parseLong((String) claims.get("memberId")))
                    .orElseThrow(() -> new NotFoundDataException("Member not found"));

            return TokenResponse.builder()
                    .accessToken(jwtTokenProvider.generateAccessToken(member))
                    .refreshToken(jwtTokenProvider.generateRefreshToken(member))
                    .useToken(true)
                    .build();
        }

        throw new TokenExpiredException("Token Expired");
    }

}
