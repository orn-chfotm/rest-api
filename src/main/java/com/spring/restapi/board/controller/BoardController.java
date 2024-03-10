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

    @GetMapping("list")
    public ResponseEntity<?> allList() {
        return SuccessResponse.entity(service.getList());
    }

    @GetMapping("get")
    public ResponseEntity<?> getBoard(@RequestBody @Valid BoardRequest requestDto) {
        return SuccessResponse.entity(service.getBoard(requestDto));
    }

    @PostMapping("put")
    public ResponseEntity<?> putBoard(@RequestBody @Valid BoardRequest requestDto) {
        return SuccessResponse.entity(service.putBoard(requestDto));
    }

    @PostMapping("upd")
    public void updBoard(@RequestBody @Valid BoardRequest requestDto) {
        service.updBoard(requestDto);
    }

    @PostMapping("del")
    public void delBoard(@RequestBody @Valid BoardRequest requestDto) {
        service.delBoard(requestDto);
    }


}
