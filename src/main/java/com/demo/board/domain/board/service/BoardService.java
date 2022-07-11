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
    public Map<String, Object> findAll(BoardCommonParams params) {
        String searchType = params.getSearchType();
        String keyword = params.getKeyword() == null ? "" : params.getKeyword();

        // 데이터 정렬 설정
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate", "id");
        // 페이징 처리 인터페이스
        // params.getPage()-1 : 첫 페이지가 0 부터 인식되기 때문에 맞춰주기 위해서
        Pageable pageable = PageRequest.of(params.getPage()-1, params.getRecordPerPage(), sort);

        // 검색 구분 분기
        Page<Board> boardPage;
        if( "title".equals(searchType) ) {
            boardPage = boardRepository.findByTitleContaining(keyword, pageable);
        }
        else if( "content".equals(searchType) ) {
            boardPage = boardRepository.findByContentContaining(keyword, pageable);
        }
        else {
            boardPage = boardRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);
        }

        // 전체 데이터 수
        int totalRecordCount = Long.valueOf(boardPage.getTotalElements()).intValue();
        // 전체 페이지 수
        int totalPageCount = boardPage.getTotalPages();
        // 화면에서 사용할 페이징 처리 정보 생성
        Pagination pagination = new Pagination(totalRecordCount, totalPageCount, params);

        params.setPagination(pagination);

        // 게시글 리스트
        List<BoardResponseDto> boardList = boardPage.getContent().stream().map(BoardResponseDto::new).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("params", params);
        response.put("list", boardList);
        return response;
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
