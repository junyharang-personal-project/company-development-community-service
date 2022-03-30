package com.devcommunity.junyharang.common.config.security.common.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 필요한 권한이 존재하지 않는 경우 403(Forbidden Error) 반환 Class
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
@Component public class JWTAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        log.info("AccessDeniedHandler를 구현한 JWTAccessDeniedHandler의 handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)가 동작 하였습니다!");

        response.sendError(HttpServletResponse.SC_FORBIDDEN);

    } // handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) 끝
} // class 끝
