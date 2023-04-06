package com.demo.board.api.board.dto;

import com.demo.board.domain.board.entity.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardListDto {

    private List<BoardResponseDto> boardDtoList = new ArrayList<>();

    private int totalPageCount;     // 전체 페이지 수
    private int totalRecordCount;   // 전체 데이터 수
    private int startPage;          // 첫 페이지 번호
    private int endPage;            // 끝 페이지 번호
    private boolean existPrevPage;  // 이전 페이지 존재 여부
    private boolean existNextPage;  // 다음 페이지 존재 여부

    @Builder
    public BoardListDto(Page<Board> boardPage, BoardCommonParams params) {
        boardDtoList = boardPage.getContent().stream().map(BoardResponseDto::new).collect(Collectors.toList());
        totalPageCount = boardPage.getTotalPages();
        totalRecordCount = Long.valueOf(boardPage.getTotalElements()).intValue();
        startPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;
        endPage = startPage + params.getPageSize() - 1;

        if( boardPage.isFirst() && boardPage.isLast() ) {
            existPrevPage = false;
            existNextPage = false;
        } else {
            existPrevPage = !boardPage.isFirst();
            existNextPage = !boardPage.isLast();
        }
    }

}
