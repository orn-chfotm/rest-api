package com.spring.restapi.member.controller;

import com.spring.restapi.member.service.MemberService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest {

    /*@Autowired
    MockMvc mockMvc;*/

    @MockBean
    MemberService memberService;

    @Test
    @DisplayName("멤버 등록 테스트")
    @Disabled
    void putMemberTest() throws Exception{
        /*MemberRequest memberReq = MemberRequest.builder()
                .email("chfotm123")
                .password("123123123")
                .name("김동현")
                .gender("man")
                .email("chfotm@gmail.com")
                .phone(null)
                .build();
        Member member = Member.builder()
                .email("chfotm@gmail.com")
                .password("123123123")
                .name("김동현")
                .gender("man")
                .email("chfotm@gmail.com")
                .build();

        given(memberService.createMember(memberReq)).willReturn(
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

        verify(memberService).createMember(memberReq);
*/
    }


}