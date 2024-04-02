package com.spring.restapi.board.controller;

import com.spring.restapi.core.dto.response.SuccessResponse;
import com.spring.restapi.board.dto.request.BoardRequest;
import com.spring.restapi.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Board Controller", description = "게시판 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService service;

    @GetMapping
    @Operation(method = "GET", summary = "게시글 리스트 조회")
    public ResponseEntity<?> allList() {
        return SuccessResponse.entity(service.getList());
    }

    @GetMapping("/{id}")
    @Operation(method = "GET", summary = "게시글 상세 조회")
    public ResponseEntity<?> getBoard(@PathVariable(value = "id") Long id ) {
        return SuccessResponse.entity(service.getBoard(id));
    }

    @PostMapping
    @Operation(method = "POST", summary = "게시글 등록")
    public ResponseEntity<?> createBoard(@Valid @RequestBody BoardRequest requestDto) {
        return SuccessResponse.entity(service.createBoard(requestDto));
    }

    @PutMapping
    @Operation(method = "PUT", summary = "게시글 수정")
    public ResponseEntity<?> updateBoard(@Valid @RequestBody BoardRequest requestDto) {
        service.updateBoard(requestDto);
        return SuccessResponse.entity(null);
    }

    @DeleteMapping("/{id}")
    @Operation(method = "DELETE", summary = "게시글 삭제")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id) {
        service.deleteBoard(id);
        return SuccessResponse.entity(null);
    }
}
