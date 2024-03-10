package com.spring.restapi.core.dto;

import java.time.LocalDateTime;

public abstract class BaseRegModResponse {

    private final String regBy;

    private final LocalDateTime regDt;

    private final String modBy;

    private final LocalDateTime modDt;

    public BaseRegModResponse(String regBy, LocalDateTime regDt, String modBy, LocalDateTime modDt){
        this.regBy = regBy;
        this.regDt = regDt;
        this.modBy = modBy;
        this.modDt = modDt;
    }

}
