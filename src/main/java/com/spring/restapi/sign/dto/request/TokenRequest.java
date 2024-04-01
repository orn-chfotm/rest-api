package com.spring.restapi.sign.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TokenRequest {

    @NotBlank(message = "Access Token is Required value")
    @Schema(description = "Access Token", example = "<JWT>")
    private String accessToken;

    @NotBlank(message = "Refresh Token is Required value")
    @Schema(description = "Refresh Token", example = "<JWT>")
    private String refreshToken;
}
