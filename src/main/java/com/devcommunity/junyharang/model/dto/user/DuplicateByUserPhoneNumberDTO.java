package com.devcommunity.junyharang.model.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 회원 가입 전 핸드폰 번호 중복 확인 및 입력값 유효성 검사 위해 사용될 DTO
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
public class DuplicateByUserPhoneNumberDTO {

    @NotBlank
    @Size(min = 10, max = 11)
    @Pattern(regexp = "^01(?:0|1|[6-9])?(\\d{3}|\\d{4})?(\\d{4})$")
//    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")     // 핸드폰 번호 Pattern
    private String userPhone;       // 회원 핸드폰 번호

} // class 끝
