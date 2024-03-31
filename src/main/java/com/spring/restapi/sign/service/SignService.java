package com.spring.restapi.sign.service;

import com.spring.restapi.sign.dto.request.SignRequest;
import com.spring.restapi.sign.dto.request.TokenRequest;
import com.spring.restapi.sign.dto.response.TokenResponse;

public interface SignService {

    TokenResponse login(SignRequest signRequest);

    TokenResponse accessToken(TokenRequest tokenRequest);

    TokenResponse refreshToken(TokenRequest tokenRequest);
}
