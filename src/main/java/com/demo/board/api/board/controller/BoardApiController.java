package com.demo.board.api.board.controller;

import com.demo.board.api.board.dto.BoardCommonParams;
import com.demo.board.api.board.dto.BoardRequestDto;
import com.demo.board.api.board.dto.BoardResponseDto;
import com.demo.board.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/boards")
public class BoardApiController {

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
    @PatchMapping("/{id}")
    public Long updateById(@PathVariable Long id, @RequestBody BoardRequestDto params) {
        return boardService.updateById(id, params);
    }

    /*
     * 게시글 전체 목록 조회 요청
     */
    @GetMapping
    public Map<String, Object> findAll(BoardCommonParams params) {
        return boardService.findAll(params);
    }

    /*
     * 게시글 상세 조회 요청
     */
    @GetMapping("/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        return boardService.findById(id);
    }

    /*
     * 게시글 삭제 요청
     */
    @DeleteMapping("/{id}")
    public Long deleteById(@PathVariable Long id) {
        return boardService.deleteById(id);
    }
}
