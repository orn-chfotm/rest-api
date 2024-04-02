package com.spring.restapi.sign.controller;

import com.spring.restapi.core.dto.response.SuccessResponse;
import com.spring.restapi.sign.dto.request.SignRequest;
import com.spring.restapi.sign.dto.request.TokenRequest;
import com.spring.restapi.sign.service.impl.SignServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Sign Controller", description = "로그인 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sign")
public class SignController {

    private final SignServiceImpl signService;


    @PostMapping("/login")
    @Operation(method = "POST", summary = "로그인", description = "로그인 처리 및 Jwt 토큰 발급")
    public ResponseEntity<?> login(@Valid @RequestBody SignRequest signRequest) {
        return SuccessResponse.entity(signService.login(signRequest));
    }


    @PostMapping("/accessToken")
    @Operation(method = "POST", summary = "accessToken 유효성 확인", description = "accessToken 유효성 확인 처리")
    public ResponseEntity<?> accessToken(@Valid @RequestBody TokenRequest tokenRequest) {
        return SuccessResponse.entity(signService.refreshToken(tokenRequest));
    }

    @PostMapping("/refreshToken")
    @Operation(method = "POST", summary = "refreshToken 유효성 확인", description = "refreshToken 유효성 확인 및 토큰 재발급")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRequest tokenRequest) {
        return SuccessResponse.entity(signService.accessToken(tokenRequest));
    }

}
