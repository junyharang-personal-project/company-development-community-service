package com.devcommunity.junyharang.service.user;

import com.devcommunity.junyharang.common.constant.DefaultResponse;
import com.devcommunity.junyharang.model.dto.user.DuplicateByIdInfoDTO;
import com.devcommunity.junyharang.model.vo.member.CustomUserDetails;

import java.util.Optional;

/**
 * 회원 가입 관련 비즈니스 로직
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.24 최초 작성
 *    주니하랑, 1.0.1, 2022.03.24 ID 중복 확인 Logic 구현
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.1, 2022.03.24 ID 중복 확인 Logic 구현
 * @See ""
 * @see <a href=""></a>
 */

public interface UserService {

    /**
     * 회원 가입 전 등록된 정보 인지 확인
     * @param username - 회원 가입 전 등록 되어 있는 정보 인지 확인을 위한 이용자 ID 정보
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    DefaultResponse duplicateUserID(DuplicateByIdInfoDTO duplicateByIdInfoDTO);

    /**
     * 회원 가입 전 등록된 정보 인지 확인
     * @param nickname - 회원 가입 전 등록 되어 있는 정보 인지 확인을 위한 이용자 별명
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    DefaultResponse duplicateUserNickName(DuplicateByIdInfoDTO duplicateByIdInfoDTO);

    /**
     * 회원 가입 전 등록된 정보 인지 확인
     * @param userEmail - 회원 가입 전 등록 되어 있는 정보 인지 확인을 위한 이용자 Email
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    DefaultResponse duplicateUserEmail(DuplicateByIdInfoDTO duplicateByIdInfoDTO);

    /**
     * 회원 가입 전 등록된 정보 인지 확인
     * @param phoneNumber - 회원 가입 전 등록 되어 있는 정보 인지 확인을 위한 이용자 핸드폰 번호
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    DefaultResponse duplicateUserPhoneNumber(DuplicateByIdInfoDTO duplicateByIdInfoDTO);

    /**
     * 회원 가입 서비스
     * @param customUserDetails - 회원 가입을 위한 이용자 입력값을 담은 Object
     * @return DefaultResponse - 서버 처리 여부에 해당하는 Status Code 반환을 위한 객체
     * @see ""
     */

    DefaultResponse signUp(CustomUserDetails customUserDetails);


} // interface 끝
