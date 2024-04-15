package com.spring.restapi.member.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.restapi.core.enums.Role;
import com.spring.restapi.core.exception.AlreadyRegisteredException;
import com.spring.restapi.core.exception.NotFoundDataException;
import com.spring.restapi.member.doamin.Member;
import com.spring.restapi.member.dto.request.MemberRequest;
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

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository,
                             JPAQueryFactory jpaQueryFactory,
                             PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.jpaQueryFactory = jpaQueryFactory;
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
    public Boolean getCheckEamil(String email) {
        return memberRepository.existsByEmail(email)
                .orElseThrow(() -> new NotFoundDataException("Member not found"));
    }

    @Override
    @Transactional
    public MemberResponse createMember(MemberRequest memberRequest) {

        if(this.getCheckEamil(memberRequest.getEmail())) {
            throw new AlreadyRegisteredException("Already Registered");
        }

        Member member = memberRepository.save(Member.builder()
                        .email(memberRequest.getEmail())
                        .password(passwordEncoder.encode(memberRequest.getPassword()))
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

}
