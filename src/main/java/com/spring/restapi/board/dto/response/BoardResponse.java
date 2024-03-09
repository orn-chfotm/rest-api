package com.spring.restapi.board.dto.response;

import com.spring.restapi.board.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class BoardResponse {

    private Long id;

    private String title;

    private String content;

    private String regBy;

    private LocalDateTime regDt;

    private String modBy;

    private LocalDateTime modDt;

    public BoardResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.regBy = board.getRegBy();
        this.regDt = board.getRegDt();
        this.modBy = board.getModBy();
        this.modDt = board.getModDt();
    }

    @Builder
    public BoardResponse(Long id, String title, String content, String regBy, LocalDateTime regDt, String modBy, LocalDateTime modDt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regBy = regBy;
        this.regDt = regDt;
        this.modBy = modBy;
        this.modDt = modDt;
    }
}
