package com.devcommunity.junyharang.common.exception.controller;

import com.devcommunity.junyharang.common.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * HTTP Status Code Error 반환을 위한 Exception Class
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
@ControllerAdvice
@RestController public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED) @ExceptionHandler(value = UnauthorizedException.class)
    public String handleBaseException(UnauthorizedException e) {
        return e.getMessage();
    } // handleBaseException(UnauthorizedException e) 끝
} // class 끝
