package com.spring.restapi.sign.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignResponse {

    private String accessToken;

    private String refreshToken;

    @Builder
    public SignResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
