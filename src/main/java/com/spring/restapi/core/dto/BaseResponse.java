package com.spring.restapi.core.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Response Base
 */
@Getter
public abstract class BaseResponse {
    private final Integer code;
    private final String message;

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
