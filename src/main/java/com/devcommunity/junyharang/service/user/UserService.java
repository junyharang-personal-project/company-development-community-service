package com.devcommunity.junyharang.service.user;

import com.devcommunity.junyharang.common.constant.DefaultResponse;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserEmailDTO;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserIDDTO;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserNickNameDTO;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserPhoneNumberDTO;
import com.devcommunity.junyharang.model.vo.member.CustomUserDetails;

/**
 * 회원 가입 관련 비즈니스 로직
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.24 최초 작성
 *    주니하랑, 1.0.1, 2022.03.27 Validation 기능 구현을 위한 중복 확인 Method 별 매개 변수 DTO 재 정의
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.1, 2022.03.27 Validation 기능 구현을 위한 중복 확인 Method 별 매개 변수 DTO 재 정의
 * @See ""
 * @see <a href=""></a>
 */

public interface UserService {

    /**
     * 회원 가입 전 등록된 ID 정보 인지 확인
     * @param duplicateByUserIDDTO - 회원 가입 전 등록 되어 있는 ID 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    DefaultResponse duplicateUserID(DuplicateByUserIDDTO duplicateByUserIDDTO);

    /**
     * 회원 가입 전 등록된 별명 정보 인지 확인
     * @param duplicateByUserInfoDTO - 회원 가입 전 등록 되어 있는 별명 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    DefaultResponse duplicateUserNickName(DuplicateByUserNickNameDTO duplicateByUserInfoDTO);

    /**
     * 회원 가입 전 등록된 Email 정보 인지 확인
     * @param duplicateByUserEmailDTO - 회원 가입 전 등록 되어 있는 EMAIL 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    DefaultResponse duplicateUserEmail(DuplicateByUserEmailDTO duplicateByUserEmailDTO);

    /**
     * 회원 가입 전 등록된 핸드폰 번호 정보 인지 확인
     * @param duplicateByUserPhoneNumberDTO - 회원 가입 전 등록 되어 있는 핸드폰 번호 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    DefaultResponse duplicateUserPhoneNumber(DuplicateByUserPhoneNumberDTO duplicateByUserPhoneNumberDTO);

    /**
     * 회원 가입 서비스
     * @param customUserDetails - 회원 가입을 위한 이용자 입력값을 담은 Object
     * @return DefaultResponse - 서버 처리 여부에 해당하는 Status Code 반환을 위한 객체
     * @see ""
     */

    DefaultResponse signUp(CustomUserDetails customUserDetails);


} // interface 끝
