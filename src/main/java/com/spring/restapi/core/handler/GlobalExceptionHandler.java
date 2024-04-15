package com.spring.restapi.core.handler;

import com.spring.restapi.core.dto.response.FailResponse;
import com.spring.restapi.core.exception.AlreadyRegisteredException;
import com.spring.restapi.core.exception.NotFoundDataException;
import com.spring.restapi.core.exception.TokenExpiredException;
import com.spring.restapi.core.exception.TokenNotFountException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final HttpStatus err = HttpStatus.BAD_REQUEST;

    /**
     * Root Exception
     */
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<FailResponse> handleException(Exception e) {
        log.error("handleException {}", e.getMessage());
        return new ResponseEntity<>(new FailResponse(err.value(), err.getReasonPhrase()), err);
    }

    /**
     * RunTime Root Exception
     */
    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<FailResponse> handleRuntimeException(RuntimeException e) {
        log.error("handleRuntimeException {}", e.getMessage());
        return new ResponseEntity<>(new FailResponse(err.value(), err.getReasonPhrase()), err);
    }

    /**
     * Custom Exception - NotFoundDateException.class
     * Key(id) 값에 맞는 데이터가 없을 경우
     */
    @ExceptionHandler(NotFoundDataException.class)
    protected ResponseEntity<FailResponse> handleNotFoundDateException(NotFoundDataException e) {
        log.error("handleNotFoundDateException {}", e.getMessage());
        return new ResponseEntity<>(
                new FailResponse(e.getCode(), e.getMessage()), e.getStatus()
        );
    }

    /**
     * Jwt Token Authentication Check Exception
     * 토큰 유효성 만료
     */
    @ExceptionHandler(TokenExpiredException.class)
    protected ResponseEntity<FailResponse> TokenExpiredException(TokenExpiredException e) {
        log.error("TokenExpiredException {}", e.getMessage());
        return new ResponseEntity<>(
                new FailResponse(e.getCode(), e.getMessage()), e.getStatus()
        );
    }

    /**
     * Token NotFoundException
     */
    @ExceptionHandler(TokenNotFountException.class)
    protected ResponseEntity<FailResponse> TokenNotFountException(TokenNotFountException e) {
        log.error("TokenNotFountException {}", e.getMessage());
        return new ResponseEntity<>(
                new FailResponse(e.getCode(), e.getMessage()), e.getStatus()
        );
    }

    /**
     * User Register -> Already Register Exception
     */
    @ExceptionHandler(AlreadyRegisteredException.class)
    protected ResponseEntity<FailResponse> AlreadyRegisteredException(AlreadyRegisteredException e) {
        log.error("AlreadyRegisteredException {}", e.getMessage());
        return new ResponseEntity<>(
                new FailResponse(e.getCode(), e.getMessage()), e.getStatus()
        );
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
