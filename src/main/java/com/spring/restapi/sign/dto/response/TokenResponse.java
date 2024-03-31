package com.spring.restapi.sign.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenResponse {

    private String accessToken;

    private String refreshToken;

    private boolean useToken;

    @Builder
    public TokenResponse(String accessToken, String refreshToken, boolean useToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.useToken = useToken;
    }
}
