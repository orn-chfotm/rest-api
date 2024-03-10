package com.spring.restapi.board.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class BoardRequest {

    private Long id;

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 40, message = "40자 이내로 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    @Size(max = 1000, message = "1000자 이내로 입력해주세요.")
    private String content;
}
