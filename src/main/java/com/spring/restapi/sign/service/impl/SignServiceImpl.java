package com.spring.restapi.sign.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.restapi.core.util.JwtTokenProvider;
import com.spring.restapi.sign.dto.request.SignRequest;
import com.spring.restapi.sign.dto.response.SignResponse;
import com.spring.restapi.sign.service.SignService;
import com.spring.restapi.member.doamin.Member;
import com.spring.restapi.member.doamin.QMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignServiceImpl implements SignService {

    private final JPAQueryFactory jpaQueryFactory;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public SignResponse login(SignRequest signRequest) {
        QMember qMember = QMember.member;

        Member member = jpaQueryFactory
                .selectFrom(qMember)
                .where(qMember.email.eq(signRequest.getEmail()))
                .fetchOne();

        System.out.println(signRequest.getEmail());
        System.out.println(signRequest.getPassword());
        System.out.println(member == null);
        System.out.println(!member.getPassword().equals(signRequest.getPassword()));
        System.out.println(member);

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

        return SignResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(member))
                .refreshToken(jwtTokenProvider.generateRefreshToken(member))
                .build();
    }
}
