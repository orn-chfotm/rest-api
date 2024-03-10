package com.spring.restapi.member.controller;

import com.spring.restapi.core.dto.response.SuccessResponse;
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

    private final MemberService service;

    @GetMapping("list")
    public ResponseEntity<?> getList() {
        return SuccessResponse.entity(service.getList());
    }

    @GetMapping("get")
    public ResponseEntity<?> getMember(@RequestBody MemberRequest request) {
        return SuccessResponse.entity(service.getMember(request));
    }

    @PostMapping("put")
    public ResponseEntity<?> putMember(@Valid @RequestBody MemberRequest request) {
        return SuccessResponse.entity(service.putMember(request));
    }

    @PutMapping("upd")
    public ResponseEntity<?> updMember(@Valid @RequestBody MemberRequest request) {
        return SuccessResponse.entity(service.updMember(request));
    }

    @DeleteMapping("del")
    public ResponseEntity<?> delMember(@RequestBody MemberRequest request) {
        if(!request.getId().isEmpty()) {
            service.delMember(request);
            return SuccessResponse.entity(null);
        }
        throw new RuntimeException();
    }

}
