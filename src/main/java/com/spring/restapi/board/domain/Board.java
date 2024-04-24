package com.spring.restapi.board.domain;

import com.spring.restapi.core.entity.BaseEntity;
import com.spring.restapi.member.doamin.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity(name = "BOARD")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"regByMember"})
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column
    @Comment("제목")
    private String title;

    @Lob
    @Column
    @Comment("내용")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regBy", referencedColumnName = "member_id", insertable = false, updatable = false)
    private Member regByMember;

    @Builder
    public Board(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
