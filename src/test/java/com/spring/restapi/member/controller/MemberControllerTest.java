package com.spring.restapi.member.controller;

import com.google.gson.Gson;
import com.spring.restapi.member.doamin.Member;
import com.spring.restapi.member.dto.request.MemberRequest;
import com.spring.restapi.member.dto.response.MemberResponse;
import com.spring.restapi.member.service.MemberService;
import com.spring.restapi.member.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @Test
    @DisplayName("멤버 등록 테스트")
    void putMemberTest() throws Exception{
        MemberRequest memberReq = MemberRequest.builder()
                .id("chfotm123")
                .pw("123123123")
                .name("김동현")
                .gender("man")
                .email("chfotm@gmail.com")
                .phone(null)
                .build();
        Member member = Member.builder()
                .id("chfotm123")
                .pw("123123123")
                .name("김동현")
                .gender("man")
                .email("chfotm@gmail.com")
                .phone(null)
                .build();

        given(memberService.insertMember(memberReq)).willReturn(
                new MemberResponse(member)
        );

        Gson gson = new Gson();
        String content = gson.toJson(memberReq);

        mockMvc.perform(
                post("/member/put")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(memberService).insertMember(memberReq);

    }


}