package com.spring.restapi.core.dto.response;

import com.spring.restapi.core.dto.BaseResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class FailResponse extends BaseResponse {

    private Map<String, String> errMap;

    public FailResponse(Integer code, String message) {
        super(code, message);
    }

    public FailResponse(Integer code, String message, Map<String, String> errMap) {
        super(code, message);
        this.errMap = errMap;
    }
}
