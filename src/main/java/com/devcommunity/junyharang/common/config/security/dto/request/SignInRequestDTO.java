package com.devcommunity.junyharang.common.config.security.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data @NoArgsConstructor @AllArgsConstructor
public class SignInRequestDTO {

    @NotBlank                                                                  // 공백이 아닌 문자 하나 이상 포함 및 Null이 아닌지 확인
    @Size(min = 1, max = 30)                                                   // 문자열 길이가 1 ~ 30사이
    @Pattern(regexp = "^[0-9a-zA-Z]+$")                                         // 영(대,소)문자, 숫자만 사용 가능 패턴 허용
    private String username;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z\\d$@$!%*?&]{8,15}$")        // 숫자, 문자, 특수문자 포함 8 ~ 15자리 비밀번호 패턴 허용
    private String password;

}
