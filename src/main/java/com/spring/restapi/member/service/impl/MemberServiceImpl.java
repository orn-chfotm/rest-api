package com.spring.restapi.member.service.impl;

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

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository repository;

    @Override
    public List<MemberResponse> getList() {
        List<Member> list = repository.findAll();
        List<MemberResponse> resList = new ArrayList<>();

        for(Member member : list) {
            resList.add(new MemberResponse(member));
        }
        return resList;
    }

    @Override
    public MemberResponse getMember(Long id) {
        Member member = repository.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Member not found"));

        return new MemberResponse(member);
    }

    @Override
    @Transactional
    public MemberResponse createMember(MemberRequest request) {

        Member member = repository.save(Member.builder()
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
        Member member = repository.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Member not found"));

        member.setEmail(request.getEmail());
        member.setPassword(request.getPassword());
        member.setName(request.getName());
        member.setGender(request.getGender());

        return new MemberResponse(member);
    }

    @Override
    public void deleteMember(Long id) {
        repository.deleteById(id);
    }

}
