package com.spring.restapi.sign.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignRequest {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    @Schema(description = "사용자 email", example = "<EMAIL>")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 12, max = 20, message = "아이디는 12-20 자로 입력해주세요.")
    @Schema(description = "사용자 password", example = "<PASSWORD>")
    private String password;

}
