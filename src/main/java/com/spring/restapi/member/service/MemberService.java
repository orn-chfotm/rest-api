package com.spring.restapi.member.service;

import com.spring.restapi.member.dto.request.MemberRequest;
import com.spring.restapi.member.dto.response.MemberResponse;

import java.util.List;

public interface MemberService {

    public List<MemberResponse> getList();

    public MemberResponse selectMember(String id);

    public MemberResponse insertMember(MemberRequest request);

    public MemberResponse updateMember(MemberRequest request);

    public void deleteMember(String id);
}
