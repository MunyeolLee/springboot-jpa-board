package com.demo.board.api.login.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginRequestDto {

    @NotBlank(message = "아이디가 입력 되지 않았습니다.")
    private String userId;

    @NotBlank(message = "비밀번호가 입력 되지 않았습니다.")
    private String password;

}
