package com.spring.restapi.core.controller;

import com.spring.restapi.core.dto.request.SignRequest;
import com.spring.restapi.core.dto.response.SuccessResponse;
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
@RequestMapping("/sign")
@RequiredArgsConstructor
public class SignController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SignRequest signRequest) {
        return SuccessResponse.entity(signRequest);
    }

}
