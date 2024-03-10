package com.spring.restapi.member.doamin;

import com.spring.restapi.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @Comment("아이디")
    private String id;


    @Column
    @Comment("비밀번호")
    private String pw;

    @Column
    @Comment("사용자 명")
    private String name;

    @Column
    @Comment("성별")
    private String gender;

    @Column
    @Comment("이메일")
    private String email;

    @Column
    @Comment("핸드폰 번호")
    private String phone;

    @Builder
    public Member(String id, String pw, String name, String gender, String email, String phone) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }
}
