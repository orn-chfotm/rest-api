package com.spring.restapi.sign.dto.request;

import lombok.Getter;

@Getter
public class TokenRequest {

    private String accessToken;

    private String refreshToken;
}
