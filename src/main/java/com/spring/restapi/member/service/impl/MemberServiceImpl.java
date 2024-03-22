package com.spring.restapi.member.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.restapi.core.exception.NotFoundDataException;
import com.spring.restapi.member.doamin.Member;
import com.spring.restapi.member.dto.request.MemberRequest;
import com.spring.restapi.member.dto.response.MemberResponse;
import com.spring.restapi.member.repository.MemberRepository;
import com.spring.restapi.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.spring.restapi.member.doamin.QMember.member;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository,
                             JPAQueryFactory jpaQueryFactory) {
        this.memberRepository = memberRepository;
        this.jpaQueryFactory = jpaQueryFactory;
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
        return jpaQueryFactory
                .select(member.count())
                .from(member)
                .where(
                        member.email.like(Email)
                )
                .fetchOne().intValue();
    }

    @Override
    @Transactional
    public MemberResponse createMember(MemberRequest request) {

        Member member = memberRepository.save(Member.builder()
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .name(request.getName())
                        .gender(request.getGender())
                .build());

        return new MemberResponse(member);
    }

    @Override
    @Transactional
    public MemberResponse updateMember(Long id, MemberRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Member not found"));

        member.setEmail(request.getEmail());
        member.setPassword(request.getPassword());
        member.setName(request.getName());
        member.setGender(request.getGender());

        return new MemberResponse(member);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

}
