package com.spring.restapi.board.controller;

import com.spring.restapi.core.dto.response.SuccessResponse;
import com.spring.restapi.board.dto.request.BoardRequest;
import com.spring.restapi.board.service.BoardService;
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

    @GetMapping(value = "list")
    public ResponseEntity<?> allList() {
        return SuccessResponse.entity(service.getList());
    }

    @GetMapping("view/{id}")
    public ResponseEntity<?> selectBoard(@PathVariable("id") Long id ) {
        return SuccessResponse.entity(service.selectBoard(id));
    }

    @PostMapping("insert")
    public ResponseEntity<?> insertBoard(@Valid @RequestBody BoardRequest requestDto) {
        return SuccessResponse.entity(service.insertBoard(requestDto));
    }

    @PutMapping("update")
    public void updateBoard(@Valid @RequestBody BoardRequest requestDto) {
        service.updateBoard(requestDto);
    }

    @DeleteMapping("{id}")
    public void deleteBoard(@PathVariable("id") Long id) {
        service.deleteBoard(id);
    }


}
