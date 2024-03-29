package com.spring.restapi.member.controller;

import com.spring.restapi.core.dto.response.SuccessResponse;
import com.spring.restapi.member.dto.request.MemberRequest;
import com.spring.restapi.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<?> getList() {
        return SuccessResponse.entity(memberService.getList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMember(@PathVariable(value = "id") Long id){
        return SuccessResponse.entity(memberService.getMember(id));
    }

    /**
     * Email 중복 Check
     */
    @PostMapping("/checkMail")
    public ResponseEntity<?> getCheckEmail(@RequestBody MemberRequest request) {
        System.out.println(request.getEmail());
        return SuccessResponse.entity(memberService.getCheckEamil(request.getEmail()));
    }

    @PostMapping
    public ResponseEntity<?> createMember(@Valid @RequestBody MemberRequest request) {
        return SuccessResponse.entity(memberService.createMember(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable(value = "id") Long id,
                                          @Valid @RequestBody MemberRequest request) {
        return SuccessResponse.entity(memberService.updateMember(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable(value = "id") Long id) {
        memberService.deleteMember(id);
        return SuccessResponse.entity(null);
    }

}
