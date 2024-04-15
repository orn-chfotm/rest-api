package com.spring.restapi.core.dto;

import java.time.LocalDateTime;

public abstract class BaseRegModResponse {

    private final String regBy;

    private final String regDt;

    private final String modBy;

    private final String modDt;

    public BaseRegModResponse(String regBy, String regDt, String modBy, String modDt){
        this.regBy = regBy;
        this.regDt = regDt;
        this.modBy = modBy;
        this.modDt = modDt;
    }

}
