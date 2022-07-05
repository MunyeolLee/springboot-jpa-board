package com.demo.board.domain.user.service;

import com.demo.board.domain.user.entity.User;
import com.demo.board.domain.user.repository.UserRepository;
import com.demo.board.global.exception.BusinessException;
import com.demo.board.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long save(User user) {
        // UserId 로 유저 조회
        Optional<User> findUser = userRepository.findByUserId(user.getUserId());
        if( findUser.isPresent() ) {
            // 조회된 유저가 있으면 오류 발생
            new BusinessException(ErrorCode.ALREADY_REGISTERED_USER);
        }

        // 유저 정보 저장
        User save = userRepository.save(user);
        return save.getId();
    }

}
