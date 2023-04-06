package com.demo.board.api.login.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests() // 인가 요청이 오면
                    .antMatchers("/css/**/*","/fonts/**/*","/plugins/**/*","/scripts/**/*") // 해당 경로들은
                    .permitAll()
                    .anyRequest() // 다른 모든 요청은
                    .authenticated()
                    .and() // 그리고 ..
                .formLogin()
                    .loginPage("/login/form")
                    .loginProcessingUrl("/auth/login") // 해당 주소로 로그인 요청을 하면 스프링 시큐리티가 가로채서 로그인을 해준다.
                    .defaultSuccessUrl("/") // 로그인 완료 후 이동 주소
                    .permitAll()
                    .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
