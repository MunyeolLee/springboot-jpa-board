package com.demo.board.api.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardCommonParams {

    private String searchType; // 검색 구분
    private String keyword;    // 검색 키워드

}
