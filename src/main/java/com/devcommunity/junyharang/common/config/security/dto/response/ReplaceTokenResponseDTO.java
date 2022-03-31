package com.devcommunity.junyharang.common.config.security.dto.response;

import lombok.Data;

/**
 * Access Token 재 발급 시 Token을 담을 DTO
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.31 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.31 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

@Data
public class ReplaceTokenResponseDTO {

    private String accessToken;

    public ReplaceTokenResponseDTO(String accessToken) { this.accessToken = accessToken; }

} // class 끝
