package com.spring.restapi.sign.service;

import com.spring.restapi.sign.dto.request.SignRequest;
import com.spring.restapi.sign.dto.response.SignResponse;

public interface SignService {

    SignResponse login(SignRequest signRequest);
}
