package com.spring.restapi.board.domain;

import com.spring.restapi.core.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Entity(name = "BOARD")
@Getter
@Setter
@NoArgsConstructor
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @Comment("제목")
    private String title;

    @Lob
    @Column
    @Comment("내용")
    private String content;

    @CreatedBy
    @Column
    @Comment("생성자")
    private String regBy;

    @LastModifiedBy
    @Column
    @Comment("수정자")
    private String modBy;

    @Builder
    public Board(Long id, String title, String content, String regBy, String modBy) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regBy = regBy;
        this.modBy = modBy;
    }
}
