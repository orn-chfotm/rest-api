package com.spring.restapi.member.service;

import com.spring.restapi.member.dto.request.LoginRequest;
import com.spring.restapi.member.dto.request.MemberRequest;
import com.spring.restapi.member.dto.response.LoginResponse;
import com.spring.restapi.member.dto.response.MemberResponse;

import java.util.List;

public interface MemberService {

    List<MemberResponse> getList();

    MemberResponse getMember(Long id);

    Boolean getCheckEamil(String Email);

    MemberResponse createMember(MemberRequest memberRequest);

    MemberResponse updateMember(Long id, MemberRequest memberRequest);

    void deleteMember(Long id);

}
