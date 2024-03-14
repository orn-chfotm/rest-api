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

    @GetMapping("view/{id}")
    public ResponseEntity<?> selectMember(@PathVariable("id") String id) {
        return SuccessResponse.entity(service.selectMember(id));
    }

    @PostMapping("insert")
    public ResponseEntity<?> insertMember(@Valid @RequestBody MemberRequest request) {
        return SuccessResponse.entity(service.insertMember(request));
    }

    @PutMapping("update")
    public ResponseEntity<?> updateMember(@Valid @RequestBody MemberRequest request) {
        return SuccessResponse.entity(service.updateMember(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMember(@PathVariable("id") String id) {
        if(!id.isEmpty()) {
            service.deleteMember(id);
            return SuccessResponse.entity(null);
        }
        throw new RuntimeException();
    }

}
