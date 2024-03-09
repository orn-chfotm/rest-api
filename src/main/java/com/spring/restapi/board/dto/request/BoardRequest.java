package com.spring.restapi.board.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequest {

    private Long id;

    private String title;

    private String content;

    private String regBy;

    private String modBy;

}
