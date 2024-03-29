package com.spring.restapi.member.dto.response;

import lombok.Builder;
import lombok.Setter;

@Setter
public class LoginResponse {

    private String accessToken;

    private String refreshToken;

    @Builder
    LoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
