package com.demo.board.domain.user;

import com.demo.board.domain.user.entity.User;
import com.demo.board.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository memberRepository;

    @Test
    void userSave() {
        String userId = "noxus";
        String password = "noxus1234";
        String name = "다리우스";

        User newUser = User.builder()
                            .userId(userId)
                            .password(password)
                            .name(name)
                            .build();

        User entity = memberRepository.save(newUser);

        assertThat(entity.getUserId()).isEqualTo(userId);
        assertThat(entity.getPassword()).isEqualTo(password);
        assertThat(entity.getName()).isEqualTo(name);
    }
}
