package com.spring.restapi.board.service;

import com.spring.restapi.board.dto.request.BoardRequest;
import com.spring.restapi.board.dto.response.BoardResponse;

import java.util.List;

public interface BoardService {

    public List<BoardResponse> getList();

    public BoardResponse getBoard(BoardRequest requestDto);

    public BoardResponse putBoard(BoardRequest requestDto);

    public void updBoard(BoardRequest requestDto);

    public void delBoard(BoardRequest requestDto);
}
