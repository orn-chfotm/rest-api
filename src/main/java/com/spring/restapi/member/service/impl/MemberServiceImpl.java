package com.spring.restapi.member.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.restapi.core.enums.Role;
import com.spring.restapi.core.exception.NotFoundDataException;
import com.spring.restapi.core.util.JwtTokenProvider;
import com.spring.restapi.member.doamin.Member;
import com.spring.restapi.member.doamin.QMember;
import com.spring.restapi.member.dto.request.LoginRequest;
import com.spring.restapi.member.dto.request.MemberRequest;
import com.spring.restapi.member.dto.response.LoginResponse;
import com.spring.restapi.member.dto.response.MemberResponse;
import com.spring.restapi.member.repository.MemberRepository;
import com.spring.restapi.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    private final JPAQueryFactory jpaQueryFactory;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository,
                             JPAQueryFactory jpaQueryFactory,
                             JwtTokenProvider jwtTokenProvider,
                             PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.jpaQueryFactory = jpaQueryFactory;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<MemberResponse> getList() {
        List<Member> list = memberRepository.findAll();
        List<MemberResponse> resList = new ArrayList<>();

        for(Member member : list) {
            resList.add(new MemberResponse(member));
        }
        return resList;
    }

    @Override
    public MemberResponse getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Member not found"));

        return new MemberResponse(member);
    }

    @Override
    public Integer getCheckEamil(String Email) {
        QMember qmember = QMember.member;

        return jpaQueryFactory
                .select(qmember.count())
                .from(qmember)
                .where(
                        qmember.email.like(Email)
                )
                .fetchOne().intValue();
    }

    @Override
    @Transactional
    public MemberResponse createMember(MemberRequest memberRequest) {


        Member member = memberRepository.save(Member.builder()
                        .email(memberRequest.getEmail())
                        .password(memberRequest.getPassword())
                        .name(memberRequest.getName())
                        .gender(memberRequest.getGender())
                        .role(Role.MEMBER.getRole())
                .build());

        return new MemberResponse(member);
    }

    @Override
    @Transactional
    public MemberResponse updateMember(Long id, MemberRequest memberRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Member not found"));

        member.setEmail(memberRequest.getEmail());
        member.setPassword(memberRequest.getPassword());
        member.setName(memberRequest.getName());
        member.setGender(memberRequest.getGender());

        return new MemberResponse(member);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        QMember qmember = QMember.member;

        Member member = jpaQueryFactory
                .select(qmember)
                .from(qmember)
                .where(
                        qmember.email.eq(loginRequest.getEmail()),
                        qmember.password.eq(loginRequest.getPassword())
                )
                .fetchOne();
        if(member == null) {
            throw new NotFoundDataException("Member not found");
        }


        return LoginResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(member))
                .refreshToken(jwtTokenProvider.generateRefreshToken(member))
                        .build();
    }

}
