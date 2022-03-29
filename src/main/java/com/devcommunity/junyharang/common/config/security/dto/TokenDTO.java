package com.devcommunity.junyharang.common.config.security.dto;

import lombok.*;

/**
 * JWT DTO
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.29 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.29 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class TokenDTO {

    private String token;

} // class 끝
