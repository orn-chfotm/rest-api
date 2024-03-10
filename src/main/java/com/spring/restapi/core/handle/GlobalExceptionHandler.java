package com.spring.restapi.core.handle;

import com.spring.restapi.core.dto.response.FailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<FailResponse> handleRuntimeException(RuntimeException e) {
        log.error("handelException {}", e.getMessage());
        final HttpStatus err = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(new FailResponse(err.value(), err.getReasonPhrase()), err);
    }

    @ExceptionHandler({SQLException.class})
    protected ResponseEntity<FailResponse> hadleSQLException(SQLException e) {
        log.error("SQLException {}", e);
        final HttpStatus err = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new FailResponse(err.value(), err.getReasonPhrase()), err);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    protected ResponseEntity<FailResponse> hadleIllegalException(IllegalArgumentException e) {
        log.error("SQLException {}", e);
        final HttpStatus err = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new FailResponse(err.value(), err.getReasonPhrase()), err);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<FailResponse> hadleNotValidException(MethodArgumentNotValidException e) {
        log.error("SQLException {}", e);

        Map<String, String> errMap = new HashMap<>();
        for(FieldError err : e.getFieldErrors()) {
            errMap.put(err.getField(), err.getDefaultMessage());
        }

        final HttpStatus err = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new FailResponse(err.value(), err.getReasonPhrase(), errMap), err);
    }
}
