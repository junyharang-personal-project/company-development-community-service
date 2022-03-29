package com.devcommunity.junyharang.common.config.security.common.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//GenericFilterBaen을 상속했을 경우 diFilter만 Overriding 해도 이용 가능
// Spring Security에서 제공하는 다른 Filter는 Int, destory도 구현해야 함.
public class JWTFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JWTFilter.class);

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private TokenProvider tokenProvider;

    public JWTFilter(TokenProvider tokenProvider) {

        logger.info("GenericFilterBean을 상속한 JWTFilter의 생성자가 호출 되었습니다! TokenProvider Bean 주입 하겠습니다!");

        this.tokenProvider = tokenProvider;

    } // 생성자 끝

    // 실제 Filtering Login Method - JWT의 인증 정보를 현재 실행 중인 SecurityContext에 저장하는 역할

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        logger.info("GenericFilterBean을 상속한 doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)가 호출 되었습니다!");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        logger.info("client Request에서 전달된 JWT를 문자열 객체 변수에 저장 하겠습니다!");

        String jwt = resolveToken(httpServletRequest);

        logger.info("JWT 값 : " + jwt);

    } // doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) 끝

    private String resolveToken(HttpServletRequest httpServletRequest) {

        logger.info("GenericFilterBean을 상속한 resolveToken(HttpServletRequest httpServletRequest)가 호출 되었습니다!");

        String bearerToken = httpServletRequest.getHeader(AUTHORIZATION_HEADER);

        logger.info("로그인 요청 Header에 `AUTHORIZATION_HEADER`가 있는지와 해당 문자열이 `Bearer `로 시작되는지 확인 하겠습니다!");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            /**
             * StringUtils.hasText() : 값이 문자열인지 아닌지 확인해서 boolean Type으로 반환
             * bearerToken이 "bearer "로 시작되면 `true`, 아니면 `false` 반환
             */

            logger.info("해당 내용과 일치합니다! ");

            logger.info("Bearer 를 제외한 나머지 문자열만 반환 할 수 있게 문자열을 자르고, 결과를 반환 하겠습니다!");
            return bearerToken.substring(6);
        } // if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) 끝

        logger.info("로그인 요청 Header에 `AUTHORIZATION_HEADER`가 있는지와 해당 문자열이 `Bearer `로 시작되는 것이 없습니다! null 반환 하겠습니다!");

        return null;
    } // resolveToken(HttpServletRequest httpServletRequest) 끝
} // class 끝
