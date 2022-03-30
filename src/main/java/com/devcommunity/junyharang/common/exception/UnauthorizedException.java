package com.devcommunity.junyharang.common.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * JWT 재발급 중 유효하지 않은 Token 사용시 사용될 Exception Class
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
public class UnauthorizedException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public UnauthorizedException(String message) {

        super(message);
    } // UnauthorizedException(String message) 끝

} // class 끝
