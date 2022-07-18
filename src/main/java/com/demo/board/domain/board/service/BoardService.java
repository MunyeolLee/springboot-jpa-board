package com.demo.board.domain.board.service;

import com.demo.board.api.board.dto.BoardCommonParams;
import com.demo.board.api.board.dto.BoardRequestDto;
import com.demo.board.api.board.dto.BoardResponseDto;
import com.demo.board.domain.board.entity.Board;
import com.demo.board.domain.board.paging.Pagination;
import com.demo.board.domain.board.repository.BoardRepository;
import com.demo.board.global.exception.BusinessException;
import com.demo.board.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    /*
     * 게시글 저장
     */
    @Transactional
    public Long save(Board params) {
        Board board = boardRepository.save(params);
        return board.getId();
    }

    /*
     * 게시글 수정
     */
    @Transactional
    public Long updateById(Long id, Board params) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
        board.update(params.getTitle(), params.getContent());
        return id;
    }

    /*
     * Like 제목으로 게시글 조회
     */
    public Page<Board> findByTitleContaining(String title, Pageable pageable) {
        return boardRepository.findByTitleContaining(title, pageable);
    }

    /*
     * Like 내용으로 게시글 조회
     */
    public Page<Board> findByContentContaining(String content, Pageable pageable) {
        return boardRepository.findByContentContaining(content, pageable);
    }

    /*
     * Like 제목 or 내용으로 게시글 조회
     */
    public Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable) {
        return boardRepository.findByTitleContainingOrContentContaining(title, content, pageable);
    }

    /*
     * 게시글 상세 조회
     */
    @Transactional
    public Board findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
        board.increaseViews();
        return board;
    }

    /*
     * 게시글 삭제
     */
    @Transactional
    public Long deleteById(Long id) {
        boardRepository.deleteById(id);
        return id;
    }
}
