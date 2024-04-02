package com.spring.restapi.member.controller;

import com.spring.restapi.core.dto.response.SuccessResponse;
import com.spring.restapi.member.dto.request.LoginRequest;
import com.spring.restapi.member.dto.request.MemberRequest;
import com.spring.restapi.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Member Controller", description = "회원 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    @Operation(method = "GET", summary = "회원 리스트 조회")
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
    @Operation(method = "POST", summary = "Email 중복 체크")
    public ResponseEntity<?> getCheckEmail(@RequestBody MemberRequest memberRequest) {
        return SuccessResponse.entity(memberService.getCheckEamil(memberRequest.getEmail()));
    }


    @PutMapping("/{id}")
    @Operation(method = "POST", summary = "회원 수정")
    public ResponseEntity<?> updateMember(@PathVariable(value = "id") Long id,
                                          @Valid @RequestBody MemberRequest memberRequest) {
        return SuccessResponse.entity(memberService.updateMember(id, memberRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(method = "DELETE", summary = "회원 삭제")
    public ResponseEntity<?> deleteMember(@PathVariable(value = "id") Long id) {
        memberService.deleteMember(id);
        return SuccessResponse.entity(null);
    }

    @PostMapping
    @Operation(method = "POST", summary = "회원 등록")
    public ResponseEntity<?> createMember(@Valid @RequestBody MemberRequest memberRequest) {
        return SuccessResponse.entity(memberService.createMember(memberRequest));
    }

}
