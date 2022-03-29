package com.devcommunity.junyharang.common.config.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SignInResponseDTO {

    private String username;
    private String nickname;
    private String userEmail;
    private boolean enable;
    private String authority;

} // class 끝
