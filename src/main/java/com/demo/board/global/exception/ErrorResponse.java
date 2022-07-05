package com.demo.board.global.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ErrorResponse {

    private int status;
    private List<String> errorMessage;
    private LocalDateTime timeStamp;

    public static ErrorResponse of(HttpStatus status, List<String> errorMessage) {
        return ErrorResponse.builder()
                .status(status.value())
                .errorMessage(errorMessage)
                .timeStamp(LocalDateTime.now())
                .build();
    }
}
