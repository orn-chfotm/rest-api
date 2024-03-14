package com.spring.restapi.member.service.impl;

import com.spring.restapi.member.doamin.Member;
import com.spring.restapi.member.dto.request.MemberRequest;
import com.spring.restapi.member.dto.response.MemberResponse;
import com.spring.restapi.member.repository.MemberRepository;
import com.spring.restapi.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public MemberResponse selectMember(String id) {
        Member member = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        return new MemberResponse(member);
    }

    @Override
    public MemberResponse insertMember(MemberRequest request) {
        Member member = repository.save(Member.builder()
                        .id(request.getId())
                        .pw(request.getPw())
                        .name(request.getName())
                        .gender(request.getGender())
                        .email(request.getEmail())
                        .phone(request.getPhone())
                .build());

        return new MemberResponse(member);
    }

    @Override
    public MemberResponse updateMember(MemberRequest request) {
        Member member = repository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        member.setPw(request.getPw());
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());
        member.setGender(request.getGender());
        member.setModDt(LocalDateTime.now());

        member = repository.save(member);

        return new MemberResponse(member);
    }

    @Override
    public void deleteMember(String id) {
        repository.deleteById(id);
    }

}
