package com.demo.board.domain.user.service;

import com.demo.board.api.login.dto.SignupRequestDto;
import com.demo.board.domain.user.entity.User;
import com.demo.board.domain.user.repository.UserRepository;
import com.demo.board.global.exception.BusinessException;
import com.demo.board.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(SignupRequestDto params) {
        // UserId 로 유저 조회
        Optional<User> findUser = userRepository.findByUserId(params.getUserId());
        if( findUser.isPresent() ) {
            // 조회된 유저가 있으면 오류 발생
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_USER);
        }

        User newUser = params.toEntity();
        newUser.encryptPassword(passwordEncoder);

        // 유저 정보 저장
        User save = userRepository.save(newUser);

        return save.getId();
    }

}
