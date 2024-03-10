package com.spring.restapi.member.service;

import com.spring.restapi.member.dto.request.MemberRequest;
import com.spring.restapi.member.dto.response.MemberResponse;

import java.util.List;

public interface MemberService {

    public List<MemberResponse> getList();

    public MemberResponse getMember(MemberRequest request);

    public MemberResponse putMember(MemberRequest request);

    public MemberResponse updMember(MemberRequest request);

    public void delMember(MemberRequest request);
}
