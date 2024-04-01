package com.spring.restapi.sign.controller;

import com.spring.restapi.core.dto.response.SuccessResponse;
import com.spring.restapi.sign.dto.request.SignRequest;
import com.spring.restapi.sign.dto.request.TokenRequest;
import com.spring.restapi.sign.service.impl.SignServiceImpl;
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
    public ResponseEntity<?> login(@Valid @RequestBody SignRequest signRequest) {
        return SuccessResponse.entity(signService.login(signRequest));
    }


    /**
     * AccessToken 유효 기간 확인
     */
    @PostMapping("/accessToken")
    public ResponseEntity<?> accessToken(@Valid @RequestBody TokenRequest tokenRequest) {
        return SuccessResponse.entity(signService.refreshToken(tokenRequest));
    }

    /**
     * RefreshToken 을 이용한 Token 재발급
     */
    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRequest tokenRequest) {
        return SuccessResponse.entity(signService.accessToken(tokenRequest));
    }

}
