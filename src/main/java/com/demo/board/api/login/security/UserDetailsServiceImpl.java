package com.demo.board.api.login.security;

import com.demo.board.domain.user.entity.User;
import com.demo.board.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername ~!");
        User user = userRepository.findByUserId(username).orElseThrow(() -> new UsernameNotFoundException("회원정보를 찾을 수 없습니다."));
        return new UserDetailImpl(user);
    }

}
