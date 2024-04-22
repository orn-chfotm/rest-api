package com.spring.restapi.board.service.impl;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.restapi.board.domain.Board;
import com.spring.restapi.board.dto.request.BoardRequest;
import com.spring.restapi.board.dto.response.BoardResponse;
import com.spring.restapi.board.repository.BoardRepository;
import com.spring.restapi.board.service.BoardService;
import com.spring.restapi.core.exception.NotFoundDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final JPAQueryFactory queryFactory;

    @Override
    public List<BoardResponse> getList() {
        List<Board> list = boardRepository.findAll();

        List<BoardResponse> responseDtoList = new ArrayList<>();
        for(Board board : list) {
            responseDtoList.add(new BoardResponse(board));
        }

        return responseDtoList;
    }

    @Override
    public BoardResponse getBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Board not found"));

        return new BoardResponse(board);
    }

    @Override
    public BoardResponse createBoard(BoardRequest requestDto) {
        Board board = Board.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();
        board = boardRepository.save(board);

        return new BoardResponse(board);
    }

    @Override
    public void updateBoard(BoardRequest requestDto) {
        Board board = boardRepository.findById(requestDto.getId())
                .orElseThrow(() -> new NotFoundDataException("Board not found"));

        board.setContent(requestDto.getContent());
        board.setTitle(requestDto.getTitle());

        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

}
