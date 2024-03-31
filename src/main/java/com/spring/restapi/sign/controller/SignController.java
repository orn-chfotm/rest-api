package com.spring.restapi.sign.controller;

import com.spring.restapi.core.dto.response.SuccessResponse;
import com.spring.restapi.sign.dto.request.SignRequest;
import com.spring.restapi.sign.service.impl.SignServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
