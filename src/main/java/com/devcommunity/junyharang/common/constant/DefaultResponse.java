package com.devcommunity.junyharang.common.constant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 응답 Message를 위한 Class
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.02.24 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.02.24 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

@Data @AllArgsConstructor @Builder
public class DefaultResponse<T> {

    // API 상태 코드
    private Integer statusCode;

    // API 부가 설명 (한글)
    private String MessageKo;

    // API 부가 설명 (영어)
    private String MessageEn;

    // API 응답 데이터
    private T data;

    // 상태 코드 + 부가 설명 반환
    public static <T> DefaultResponse<T> response(final Integer statusCode, final String MessageKo, final String MessageEn){
        return (DefaultResponse<T>) DefaultResponse.builder()
                .statusCode(statusCode)
                .MessageKo(MessageKo)
                .MessageEn(MessageEn)
                .build();
    }   // response(final Integer statusCode, final String MessageKo, final String MessageEn) 끝

    // 상태 코드 + 부가 설명 + 응답 데이터 반환
    public static <T> DefaultResponse<T> response(final Integer statusCode, final String MessageKo, final String MessageEn, final T data){
        return (DefaultResponse<T>) DefaultResponse.builder()
                .statusCode(statusCode)
                .MessageKo(MessageKo)
                .MessageEn(MessageEn)
                .data(data)
                .build();
    }   // response(final Integer statusCode, final String MessageKo, final String MessageEn, final T data) 끝
} // class 끝
