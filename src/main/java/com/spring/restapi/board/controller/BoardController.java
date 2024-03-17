package com.spring.restapi.board.controller;

import com.spring.restapi.core.dto.response.SuccessResponse;
import com.spring.restapi.board.dto.request.BoardRequest;
import com.spring.restapi.board.service.BoardService;
import com.spring.restapi.core.exception.EmptyIdValueException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService service;

    @GetMapping
    public ResponseEntity<?> allList() {
        return SuccessResponse.entity(service.getList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable(value = "id", required = false) Long id ) {
        if(id == null) {
            throw new EmptyIdValueException("BoardController.getBoard::");
        }
        return SuccessResponse.entity(service.getBoard(id));
    }

    @PostMapping
    public ResponseEntity<?> createBoard(@Valid @RequestBody BoardRequest requestDto) {
        return SuccessResponse.entity(service.createBoard(requestDto));
    }

    @PutMapping
    public void updateBoard(@Valid @RequestBody BoardRequest requestDto) {
        service.updateBoard(requestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable("id") Long id) {
        if(id == null) {
            throw new EmptyIdValueException("BoardController.getBoard::");
        }
        service.deleteBoard(id);
    }


}
