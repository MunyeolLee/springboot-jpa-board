package com.demo.board.api.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/view/{id}")
    public String openBoardView(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "board/view";
    }
}
