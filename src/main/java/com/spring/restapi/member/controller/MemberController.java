package com.spring.restapi.member.controller;

import com.spring.restapi.core.dto.response.SuccessResponse;
import com.spring.restapi.core.exception.EmptyIdValueException;
import com.spring.restapi.member.dto.request.MemberRequest;
import com.spring.restapi.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public ResponseEntity<?> getMember(@PathVariable(value = "id", required = false) Long id){
        if(id == null){
            throw new EmptyIdValueException("MemberController.getMember::");
        }
        return SuccessResponse.entity(memberService.getMember(id));
    }

    @PostMapping
    public ResponseEntity<?> createMember(@Valid @RequestBody MemberRequest request) {
        return SuccessResponse.entity(memberService.createMember(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable(value = "id", required = false) Long id,
                                          @Valid @RequestBody MemberRequest request) {
        if(id == null){
            throw new EmptyIdValueException("MemberController.updateMember::");
        }
        return SuccessResponse.entity(memberService.updateMember(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable(value = "id", required = false) Long id) {
        if(id == null){
            throw new EmptyIdValueException("MemberController.deleteMember::");
        }
        memberService.deleteMember(id);
        return SuccessResponse.entity(null);
    }

}
