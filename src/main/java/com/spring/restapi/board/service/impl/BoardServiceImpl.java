package com.spring.restapi.board.service.impl;


import com.spring.restapi.board.domain.Board;
import com.spring.restapi.board.repository.BoardRepository;
import com.spring.restapi.board.dto.request.BoardRequest;
import com.spring.restapi.board.dto.response.BoardResponse;
import com.spring.restapi.board.service.BoardService;
import com.spring.restapi.core.exception.NotFoundDataException;
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
    public BoardResponse getBoard(Long id) {
        Board board = repository.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Board not found"));

        return new BoardResponse(board);
    }

    @Override
    public BoardResponse createBoard(BoardRequest requestDto) {
        Board board = Board.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();
        board = repository.save(board);

        return new BoardResponse(board);
    }

    @Override
    public void updateBoard(BoardRequest requestDto) {
        Board board = repository.findById(requestDto.getId())
                .orElseThrow(() -> new NotFoundDataException("Board not found"));

        board.setContent(requestDto.getContent());
        board.setTitle(requestDto.getTitle());

        repository.save(board);
    }

    @Override
    public void deleteBoard(Long id) {
        repository.deleteById(id);
    }

}
