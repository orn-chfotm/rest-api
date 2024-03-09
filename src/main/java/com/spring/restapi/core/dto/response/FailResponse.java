package com.spring.restapi.core.dto.response;

import com.spring.restapi.core.dto.BaseResponse;

public class FailResponse extends BaseResponse {

    public FailResponse(Integer code, String message) {
        super(code, message);
    }
}
