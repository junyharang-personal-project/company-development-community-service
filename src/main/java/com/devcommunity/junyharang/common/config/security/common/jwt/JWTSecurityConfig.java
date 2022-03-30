package com.devcommunity.junyharang.common.config.security.common.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * JWT 설정 Class
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.30 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.30 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

@Slf4j @RequiredArgsConstructor
public class JWTSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        log.info("SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>를 상속한 JWTSecurityConfig의 configure(HttpSecurity http)가 호출 되었습니다!");
        log.info("Security Logic에 JWTFilter를 등록하기 위한 작업을 실시하겠습니다!");

        JWTFilter customFilter = new JWTFilter(tokenProvider);

        log.info("Security Logic에 JWTFilter 등록 하겠습니다!");
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);

    } // configure(HttpSecurity http) 끝
} // class 끝
