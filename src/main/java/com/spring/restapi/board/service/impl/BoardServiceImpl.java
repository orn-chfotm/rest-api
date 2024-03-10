package com.spring.restapi.board.service.impl;


import com.spring.restapi.board.domain.Board;
import com.spring.restapi.board.repository.BoardRepository;
import com.spring.restapi.board.dto.request.BoardRequest;
import com.spring.restapi.board.dto.response.BoardResponse;
import com.spring.restapi.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository repository;

    @Override
    public List<BoardResponse> getList() {
        List<Board> list = repository.findAll();

        List<BoardResponse> responseDtoList = new ArrayList<>();
        for(Board board : list) {
            responseDtoList.add(new BoardResponse(board));
        }

        return responseDtoList;
    }

    @Override
    public BoardResponse getBoard(BoardRequest requestDto) {
        Board board = repository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        return new BoardResponse(board);
    }

    @Override
    public BoardResponse putBoard(BoardRequest requestDto) {
        Board board = Board.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();
        board = repository.save(board);

        return new BoardResponse(board);
    }

    @Override
    public void updBoard(BoardRequest requestDto) {
        Board board = repository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        board.setContent(requestDto.getContent());
        board.setTitle(requestDto.getTitle());
        board.setModDt(LocalDateTime.now());

        repository.save(board);
    }

    @Override
    public void delBoard(BoardRequest requestDto) {
        repository.deleteById(requestDto.getId());
    }

}
