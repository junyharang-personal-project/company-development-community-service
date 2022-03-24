package com.devcommunity.junyharang.service.user;

import com.devcommunity.junyharang.common.constant.DefaultResponse;
import com.devcommunity.junyharang.model.vo.member.CustomUserDetails;

/**
 * 회원 가입 관련 비즈니스 로직
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.24 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.24 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

public interface UserService {

    /**
     * 회원 가입 서비스
     * @param customUserDetails - 회원 가입을 위한 이용자 입력값을 담은 Object
     * @return DefaultResponse - 서버 처리 여부에 해당하는 Status Code 반환을 위한 객체
     * @see ""
     */

    DefaultResponse signUp(CustomUserDetails customUserDetails);

} // interface 끝
