package com.demo.board.api.board.dto;

import com.demo.board.domain.board.paging.Pagination;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardCommonParams {

    private String searchType;    // 검색 구분
    private String keyword;       // 검색 키워드
    private int page;             // 현재 페이지 번호
    private int recordPerPage;    // 페이지당 출력할 데이터 개수
    private int pageSize;         // 화면 하단에 출력할 페이지 개수
    private Pagination pagination;// 페이지 처리 정보

    @Override
    public String toString() {
        return "BoardCommonParams{" +
                "searchType='" + searchType + '\'' +
                ", keyword='" + keyword + '\'' +
                ", page=" + page +
                ", recordPerPage=" + recordPerPage +
                ", pageSize=" + pageSize +
                ", pagination=" + pagination +
                '}';
    }
}
