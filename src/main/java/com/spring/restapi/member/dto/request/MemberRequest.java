package com.spring.restapi.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberRequest {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    @Schema(description = "사용자 password", example = "default@gmail.com")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 12, max = 20, message = "아이디는 12-20 자로 입력해주세요.")
    @Schema(description = "사용자 password", example = "<PASSWORD>")
    private String password;

    @Schema(description = "사용자 name", example = "김동현")
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @Schema(description = "사용자 성별", example = "man, woman")
    @NotBlank(message = "성별을 선택해주세요.")
    @Pattern(regexp = "(man|woman)", message = "정확한 값을 입력해주세요.")
    private String gender;

    /*
    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
    private String phone;*/
}
