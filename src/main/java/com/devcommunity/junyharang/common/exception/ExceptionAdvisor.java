package com.devcommunity.junyharang.common.exception;

import com.devcommunity.junyharang.common.constant.DefaultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * 유효성 검사 관련 Exception 처리 Class
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.27 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.27 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

@Slf4j
@ControllerAdvice       // 전역 설정을 위한 Annotaion
@RestController
public class ExceptionAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultResponse<String>> processValidationException(MethodArgumentNotValidException exception) {

        log.info("ExceptionAdvisor의 processValidationException(MethodArgumentNotValidException exception)이 호출 되었습니다!");
        log.info("Validation 중 발생한 문제(Exception)값을 BindingResult 객체 Type bindingResult 변수에 담겠습니다!");

        BindingResult bindingResult = exception.getBindingResult();

        log.info("문자열을 합치기 위해 StringBuilder 객체를 생성하겠습니다!");
        StringBuffer stringBuffer = new StringBuffer();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {

            stringBuffer.append("[");
            stringBuffer.append(fieldError.getField());
            stringBuffer.append("](은)는 ");
            stringBuffer.append(fieldError.getDefaultMessage()+".");
            stringBuffer.append(" 입력된 값 : [");
            stringBuffer.append(fieldError.getRejectedValue());
            stringBuffer.append("] 입니다!");

        } // for (FieldError fieldError : bindingResult.getFieldErrors()) 끝

        log.info("ResponEntity를 이용하여 400 Code와 함께 Exception 내용을 반환하겠습니다!");
        return new ResponseEntity<>(DefaultResponse.response(HttpStatus.BAD_REQUEST.value(), "유효성 검사 실패", stringBuffer.toString()), HttpStatus.BAD_REQUEST);

    } // processValidationException(MethodArgumentNotValidException exception) 끝

} // class 끝

