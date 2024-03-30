package com.spring.restapi.core.service.Impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.restapi.core.dto.request.SignRequest;
import com.spring.restapi.core.dto.response.SignResponse;
import com.spring.restapi.core.service.SignService;
import com.spring.restapi.core.util.JwtTokenProvider;
import com.spring.restapi.member.doamin.Member;
import com.spring.restapi.member.doamin.QMember;
import com.spring.restapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignServiceImpl implements SignService {

    private final JPAQueryFactory jpaQueryFactory;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    public SignResponse login(SignRequest signrequest) {
        QMember qMember = QMember.member;

        Member member = jpaQueryFactory
                .selectFrom(qMember)
                .where(qMember.email.eq(signrequest.getEmail()))
                .fetchOne();

        if(member!= null || !member.getPassword().equals(signrequest.getPassword())) {
            throw new RuntimeException("회원 정보가 일치하지 않습니다.");
        }
        /*
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                member.getId(),
                member.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        */
        return SignResponse.builder()
                .asseccToken(jwtTokenProvider.generateAccessToken(member))
                .refreshToken(jwtTokenProvider.generateRefreshToken(member))
                .build();
    }
}
