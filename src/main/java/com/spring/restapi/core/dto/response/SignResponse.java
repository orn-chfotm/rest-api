package com.spring.restapi.core.dto.response;

import lombok.Builder;
import lombok.Setter;

@Setter
public class SignResponse {

    private String asseccToken;

    private String refreshToken;

    @Builder
    public SignResponse(String asseccToken, String refreshToken) {
        this.asseccToken = asseccToken;
        this.refreshToken = refreshToken;
    }

}
