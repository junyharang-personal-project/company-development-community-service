package com.devcommunity.junyharang.common.config.security.common.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 유효한 자격증명을 거치지 않고, 접근하려 할 때, 401(Unauthorized Error) 반환 Class
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

@Slf4j
@Component public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        log.info("AuthenticationEntryPoint를 구현한 JWTAuthenticationEntryPoint의 commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)가 동작 하였습니다!");

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

    } // commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) 끝
} // class 끝