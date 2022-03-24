package com.devcommunity.junyharang.common.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 설정 Class
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.24 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.2, 1.0.0, 2022.03.24 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

@Configuration  @RequiredArgsConstructor @Slf4j
@EnableWebSecurity  // Spring Seucurity 활성화
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 회원 가입 Logic에서 회원 비밀번호 함호화 할 수 있도록 bean 등록
    @Bean public PasswordEncoder passwordEncoder() {
        log.info("WebSecurityConfigurerAdapter를 상속한 SecurityConfig의 passwordEncoder()가 호출 되었습니다!");

        return new BCryptPasswordEncoder();
    } // passwordEncoder() 끝

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        log.info("WebSecurityConfigurerAdapter를 상속한 SecurityConfig의 configure(HttpSecurity http)가 호출 되었습니다!");

        // Token 방식으로 CSRF 비활성화
        http
                .csrf().disable()   // csrf : 자신의 의지와 무관하게 특정 Web Site(등록, 수정, 삭제 등)을 요청하는 공격

                .sessionManagement()    // Session 방식이 아닌 Token 방식 사용으로 stateless로 설정
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()    // HttpServletRequest를 이용하는 요청들에 대한 접근 권한 설정
                .antMatchers("/", "/home","/main","/swagger-ui/index.html").permitAll()  // 해당 URI에 요청은 인증없이 접근 허용
                .antMatchers("/api/user/**").permitAll()        // 회원 관련 처리에 해당하는 URI 요청 인증없이 접근 허용
                .antMatchers("/manager/**").authenticated()     // 관리자 Page 접근시 인증을 받아야 접근 가능
                .antMatchers("/admin/**").authenticated();
    } // configure(HttpSecurity http) 끝
} // class 끝
