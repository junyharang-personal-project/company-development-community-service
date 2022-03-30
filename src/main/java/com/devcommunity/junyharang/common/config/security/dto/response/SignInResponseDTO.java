package com.devcommunity.junyharang.common.config.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SignInResponseDTO {

    private String accessToken;
    private String refreshToken;

    private int userId;
    private String nickname;
    private boolean enable;
    private String authority;

    public SignInResponseDTO(String accessToken, String refreshToken, int userId, String authority, String nickname) {

        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.authority = authority;
        this.nickname = nickname;

    } // SignInResponseDTO(String accessToken, String refreshToken, int userId, String authority, String nickname) 끝
} // class 끝
