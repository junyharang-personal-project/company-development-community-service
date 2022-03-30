package com.devcommunity.junyharang.common.config.security.dto;

import lombok.*;

/**
 * JWT DTO
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.29 최초 작성
 *    주니하랑, 1.0.1, 2022.03.30 access Token과 Refresh Token 분리 생성으로 인한 Member 변수 추가
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.1, 2022.03.30 access Token과 Refresh Token 분리 생성으로 인한 Member 변수 추가
 * @see <a href="https://wnwngus.tistory.com/65"></a>
 */

@Getter @Setter @Builder @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenDTO {

    private String accessToken;
    private String refreshToken;

    @Builder public TokenDTO(String accessToken, String refreshToken) {

        this.accessToken = accessToken;
        this.refreshToken = refreshToken;

    } // TokenDTO(String accessToken, String refreshToken) 끝

} // class 끝
