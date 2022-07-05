package com.demo.board.api.board.controller;

import com.demo.board.api.board.dto.BoardRequestDto;
import com.demo.board.api.board.dto.BoardResponseDto;
import com.demo.board.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/boards")
public class BoardController {

    private final BoardService boardService;

    /*
     * 게시글 저장 요청
     */
    @PostMapping
    public Long save(@Valid @RequestBody BoardRequestDto params) {
        return boardService.save(params);
    }

    /*
     * 게시글 수정 요청
     */
    @PostMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardRequestDto params) {
        return boardService.update(id, params);
    }

    /*
     * 게시글 전체 목록 조회 요청
     */
    @GetMapping
    public List<BoardResponseDto> findAll() {
        return boardService.findAll();
    }

    /*
     * 게시글 상세 조회 요청
     */
    @GetMapping("/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        return boardService.findById(id);
    }
}
