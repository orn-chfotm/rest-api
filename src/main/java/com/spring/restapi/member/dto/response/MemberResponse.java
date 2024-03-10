package com.spring.restapi.member.dto.response;

import com.spring.restapi.core.dto.BaseRegModResponse;
import com.spring.restapi.member.doamin.Member;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class MemberResponse extends BaseRegModResponse {

    private String id;

    private String name;

    private String gender;

    private String email;

    private String phone;

    public MemberResponse(Member member) {
        super(member.getRegBy(), member.getRegDt(), member.getModBy(), member.getModDt());
        this.id = member.getId();
        this.name = member.getName();
        this.gender = member.getGender();
        this.email = member.getEmail();
        this.phone = member.getPhone();
    }
}
