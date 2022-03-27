package com.devcommunity.junyharang.model.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 회원 가입 전 ID 중복 확인 및 입력값 유효성 검사 위해 사용될 DTO
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.25 최초 작성
 *    주니하랑, 1.0.1, 2022.03.27 Validation을 위한 내용 추가 및 DTO 분리
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.1, 2022.03.27 Validation을 위한 내용 추가 및 DTO 분리
 * @See ""
 * @see <a href=""></a>
 */

@Setter @Getter @ToString
public class DuplicateByUserIDDTO {

    @NotBlank                                                                  // 공백이 아닌 문자 하나 이상 포함 및 Null이 아닌지 확인
    @Size(min = 1, max = 30)                                                   // 문자열 길이가 1 ~ 30사이
    @Pattern(regexp = "^[0-9a-zA-Z]+$")                                         // 영(대,소)문자, 숫자만 사용 가능 패턴 허용
    private String username;                                                   // 회원 ID

} // class 끝
