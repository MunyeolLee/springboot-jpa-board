package com.demo.board.api.login.contoller;

import com.demo.board.api.login.dto.SignupRequestDto;
import com.demo.board.domain.user.service.UserService;
import com.demo.board.global.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class LoginApiController {

    private final UserService userService;

    @PostMapping(value = "/signup")
    public ResponseEntity<ResponseObject<Object>> signup(@Valid @RequestBody SignupRequestDto params) {
        userService.save(params);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .status(HttpStatus.OK.value())
                        .message("회원가입이 완료 되었습니다.")
                        .data("")
                        .build()
        );
    }

}
