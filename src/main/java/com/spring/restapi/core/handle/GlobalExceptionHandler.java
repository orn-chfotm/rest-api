package com.spring.restapi.core.handle;

import com.spring.restapi.core.dto.response.FailResponse;
import com.spring.restapi.core.exception.EmptyIdValueException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final HttpStatus err = HttpStatus.BAD_REQUEST;

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<FailResponse> handleException(Exception e) {
        log.error("handleException {}", e.getMessage());
        return new ResponseEntity<>(new FailResponse(err.value(), err.getReasonPhrase()), err);
    }

    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<FailResponse> handleRuntimeException(RuntimeException e) {
        log.error("handleRuntimeException {}", e.getMessage());
        return new ResponseEntity<>(new FailResponse(err.value(), err.getReasonPhrase()), err);
    }

    /**
     * Key id 값이 빈 값일 경우 Exception Handle
     */
    @ExceptionHandler({EmptyIdValueException.class})
    protected ResponseEntity<FailResponse> handleEmptyIdValueException(EmptyIdValueException e) {
        log.error("handleEmptyIdValueException {}", e);
        String message = e.getMessage().concat("Empty id value");
        return new ResponseEntity<>(new FailResponse(err.value(), message), err);
    }

    /**
     * @Valid Exception 핸들링
     * return Map<String, String>
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<FailResponse> handleNotValidException(MethodArgumentNotValidException e) {
        log.error("handleNotValidException {}", e);

        Map<String, String> errMap = new HashMap<>();
        for(FieldError err : e.getFieldErrors()) {
            errMap.put(err.getField(), err.getDefaultMessage());
        }

        return new ResponseEntity<>(new FailResponse(err.value(), err.getReasonPhrase(), errMap), err);
    }
}
