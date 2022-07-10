package com.demo.board.domain.board.service;

import com.demo.board.api.board.dto.BoardCommonParams;
import com.demo.board.api.board.dto.BoardRequestDto;
import com.demo.board.api.board.dto.BoardResponseDto;
import com.demo.board.domain.board.entity.Board;
import com.demo.board.domain.board.repository.BoardRepository;
import com.demo.board.global.exception.BusinessException;
import com.demo.board.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    /*
     * 게시글 저장
     */
    @Transactional
    public Long save(BoardRequestDto params) {
        Board board = boardRepository.save(params.toEntity());
        return board.getId();
    }

    /*
     * 게시글 수정
     */
    @Transactional
    public Long updateById(Long id, BoardRequestDto params) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
        board.update(params.getTitle(), params.getContent());
        return id;
    }

    /*
     * 전체 게시글 목록 조회
     */
    public List<BoardResponseDto> findAll(BoardCommonParams params) {
        List<Board> boardList;

        String searchType = params.getSearchType();
        String keyword = params.getKeyword() == null ? "" : params.getKeyword();

        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");

        if( "title".equals(searchType) ) {
            boardList = boardRepository.findByTitleContaining(keyword, sort);
        }
        else if( "content".equals(searchType) ) {
            boardList = boardRepository.findByContentContaining(keyword, sort);
        }
        else {
            boardList = boardRepository.findByTitleContainingOrContentContaining(keyword, keyword, sort);
        }

        return boardList.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    /*
     * 게시글 상세 조회
     */
    @Transactional
    public BoardResponseDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
        board.increaseViews();
        return new BoardResponseDto(board);
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
