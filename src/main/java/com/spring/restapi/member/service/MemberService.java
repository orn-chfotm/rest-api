package com.spring.restapi.member.service;

import com.spring.restapi.member.dto.request.MemberRequest;
import com.spring.restapi.member.dto.response.MemberResponse;

import java.util.List;

public interface MemberService {

    List<MemberResponse> getList();

    MemberResponse getMember(Long id);

    MemberResponse createMember(MemberRequest request);

    MemberResponse updateMember(Long id, MemberRequest request);

    void deleteMember(Long id);
}
