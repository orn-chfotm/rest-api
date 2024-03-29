package com.spring.restapi.core.service;

import com.spring.restapi.core.dto.request.SignRequest;
import com.spring.restapi.core.dto.response.SignResponse;

public interface SignService {

    public SignResponse login(SignRequest signrequest);
}
