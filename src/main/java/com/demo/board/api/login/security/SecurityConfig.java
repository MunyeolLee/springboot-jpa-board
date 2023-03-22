package com.demo.board.api.login.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests() // 인가 요청이 오면
//                .antMatchers("/auth/*", "/css/**/*","/fonts/**/*","/plugins/**/*","/scripts/**/*") // 해당 경로들은
                .antMatchers("/**/*") // 회원가입 기능 구현 전까지 패스..
                .permitAll() // 접근을 허용한다.
                .anyRequest() // 다른 모든 요청은
                .authenticated() // 인증이 필요하다.
                .and() // 그리고 ..
                .formLogin()
                .loginProcessingUrl("/auth/login") // 해당 주소로 로그인 요청을 하면 스프링 시큐리티가 가로채서 로그인을 해준다.
                .defaultSuccessUrl("/board/list") // 로그인 완료 후 이동 주소
                .and().build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
