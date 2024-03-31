package com.spring.restapi.member.doamin;

import com.spring.restapi.core.entity.BaseEntity;
import com.spring.restapi.core.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @Comment("이메일")
    private String email;

    @Column
    @Comment("비밀번호")
    private String password;

    @Column
    @Comment("사용자 명")
    private String name;

    @Column
    @Comment("성별")
    private String gender;

    @Column
    @Comment("권한")
    private String role;

    @Builder
    public Member(Long id, String email, String password, String name, String gender, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.role = role;
    }
}
