package com.spring.restapi.board.dto.response;

import com.spring.restapi.board.domain.Board;
import com.spring.restapi.core.dto.BaseRegModResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class BoardResponse extends BaseRegModResponse {

    private Long id;

    private String title;

    private String content;

    private String regByName;

    @Builder
    public BoardResponse(Board board) {
        super(board.getRegBy(), board.getRegDt(), board.getModBy(), board.getModDt());
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
