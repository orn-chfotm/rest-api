package com.spring.restapi.sign.controller;

import com.spring.restapi.core.dto.response.SuccessResponse;
import com.spring.restapi.sign.dto.request.SignRequest;
import com.spring.restapi.sign.dto.request.TokenRequest;
import com.spring.restapi.sign.service.impl.SignServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sign")
public class SignController {

    private final SignServiceImpl signService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SignRequest signRequest) {
        return SuccessResponse.entity(signService.login(signRequest));
    }

    /**
     * RefreshToken 을 이용한 Token 재발급
     */
    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRequest tokenRequest) {
        return SuccessResponse.entity(signService.accessToken(tokenRequest));
    }

    /**
     * AccessToken 유효 기간 확인
     */
    @PostMapping("/accessToken")
    public ResponseEntity<?> accessToken(@RequestBody TokenRequest tokenRequest) {
        return SuccessResponse.entity(signService.refreshToken(tokenRequest));
    }

}
