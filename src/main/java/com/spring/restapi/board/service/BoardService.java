package com.spring.restapi.board.service;

import com.spring.restapi.board.dto.request.BoardRequest;
import com.spring.restapi.board.dto.response.BoardResponse;

import java.util.List;

public interface BoardService {

    public List<BoardResponse> getList();

    public BoardResponse selectBoard(Long id);

    public BoardResponse insertBoard(BoardRequest requestDto);

    public void updateBoard(BoardRequest requestDto);

    public void deleteBoard(Long id);
}
