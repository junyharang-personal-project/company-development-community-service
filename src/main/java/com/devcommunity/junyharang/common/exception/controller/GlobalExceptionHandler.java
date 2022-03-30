package com.devcommunity.junyharang.common.exception.controller;

import com.devcommunity.junyharang.common.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice
@RestController public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED) @ExceptionHandler(value = UnauthorizedException.class)
    public String handleBaseException(UnauthorizedException e) {
        return e.getMessage();
    } // handleBaseException(UnauthorizedException e) 끝
} // class 끝
