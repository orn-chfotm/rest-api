package com.spring.restapi.core.dto.response;

import com.spring.restapi.core.dto.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SuccessResponse<T> extends BaseResponse {

    private final T data;

    @Builder
    public SuccessResponse(Integer code, String message, T data) {
        super(code, message);
        this.data = data;
    }
    public static <T> ResponseEntity<?> entity(T data) {
        final HttpStatus success = HttpStatus.OK;
        return new ResponseEntity<>(new SuccessResponse<>(success.value(), success.getReasonPhrase(), data), success);
    }
}
