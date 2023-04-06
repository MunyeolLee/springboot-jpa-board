package com.demo.board.api.login.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {

    @GetMapping("/login/form")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/board/list";
    }
}
