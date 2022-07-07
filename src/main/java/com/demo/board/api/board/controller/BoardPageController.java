package com.demo.board.api.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardPageController {

    /*
     * 게시판 목록 화면
     */
    @GetMapping("/list")
    public String openBoardList() {
        return "board/list";
    }

    /*
     * 게시판 작성 화면
     */
    @GetMapping("/write")
    public String openBoardWrite() {
        return "board/write";
    }

}
