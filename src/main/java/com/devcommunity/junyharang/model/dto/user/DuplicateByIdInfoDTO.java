package com.devcommunity.junyharang.model.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원 가입 전 중복 확인을 위해 사용될 DTO
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.25 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.25 최초 작성
 * @See ""
 * @see <a href=""></a>
*/

@Setter @Getter @ToString
public class DuplicateByIdInfoDTO {

    private String username;        // 회원 ID

    private String nickname;        // 회원 별명

    private String userEmail;       // 회원 Email

    private String userPhone;       // 회원 핸드폰 번호

} // class 끝
