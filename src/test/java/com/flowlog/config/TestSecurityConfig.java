package com.flowlog.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
public class TestSecurityConfig {

    @Bean
    public SecurityFilterChain testSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/**")
                .csrf(csrf -> csrf.disable()) // ✅ CSRF 완전 비활성화
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // ✅ 모든 요청 허용
        return http.build();
    }
}
