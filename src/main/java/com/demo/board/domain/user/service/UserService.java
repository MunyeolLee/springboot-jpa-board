package com.demo.board.domain.user.service;

import com.demo.board.api.login.dto.LoginRequestDto;
import com.demo.board.api.login.dto.SignupRequestDto;
import com.demo.board.domain.user.entity.User;
import com.demo.board.domain.user.repository.UserRepository;
import com.demo.board.global.exception.BusinessException;
import com.demo.board.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long save(SignupRequestDto params) {
        // UserId 로 유저 조회
        Optional<User> findUser = userRepository.findByUserId(params.getUserId());
        if( findUser.isPresent() ) {
            // 조회된 유저가 있으면 오류 발생
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_USER);
        }

        // 유저 정보 저장
        User save = userRepository.save(params.toEntity());

        return save.getId();
    }

    public User login(LoginRequestDto params) {
        // UserId 유저 조회
        Optional<User> findUser = userRepository.findByUserId(params.getUserId());
        if( findUser.isEmpty() ) {
            // 조회된 유저가 없으면 오류 발생
            throw new BusinessException(ErrorCode.CAN_NOT_FOUND_USER);
        }

        return findUser.get();
    }
}
