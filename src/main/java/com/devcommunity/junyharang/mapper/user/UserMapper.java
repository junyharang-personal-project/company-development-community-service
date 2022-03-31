package com.devcommunity.junyharang.mapper.user;

import com.devcommunity.junyharang.model.dto.user.DuplicateByUserEmailDTO;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserIDDTO;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserNickNameDTO;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserPhoneNumberDTO;
import com.devcommunity.junyharang.model.vo.member.CustomUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 회원 관련 Mapper
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.29 최초 작성
 *    주니하랑, 1.0.1, 2022.03.31 Refresh Token DB 저장을 위한 추상 Method 추가
 *    주니하랑, 1.0.2, 2022.03.31 Access Token 재 발급 기능 구현 완료
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.2, 2022.03.31 Access Token 재 발급 기능 구현 완료
 * @See ""
 * @see <a href=""></a>
 */

@Mapper @Repository public interface UserMapper {

    /**
     * 회원 가입 전 등록된 ID 정보 인지 확인
     * @param duplicateByUserIDDTO - 회원 가입 전 등록 되어 있는 ID 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    Optional<DuplicateByUserNickNameDTO> findByUserID(DuplicateByUserIDDTO duplicateByUserIDDTO);

    /**
     * 회원 가입 전 등록된 별명 정보 인지 확인
     * @param duplicateByUserNickNameDTO - 회원 가입 전 등록 되어 있는 별명 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    Optional<DuplicateByUserNickNameDTO> findByUserNickName(DuplicateByUserNickNameDTO duplicateByUserNickNameDTO);

    /**
     * 회원 가입 전 등록된 Email 정보 인지 확인
     * @param duplicateByUserEmailDTO - 회원 가입 전 등록 되어 있는 EMAIL 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    Optional<DuplicateByUserNickNameDTO> findByUserEmail(DuplicateByUserEmailDTO duplicateByUserEmailDTO);

    /**
     * 회원 가입 전 등록된 핸드폰 번호 정보 인지 확인
     * @param duplicateByUserPhoneNumberDTO - 회원 가입 전 등록 되어 있는 핸드폰 번호 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    Optional<DuplicateByUserNickNameDTO> findByUserPhoneNumber(DuplicateByUserPhoneNumberDTO duplicateByUserPhoneNumberDTO);

    /**
     * 회원 가입을 위한 추상 Method
     * @param customUserDetails - 회원 가입 시 이용자가 입력한 정보 담은 객체
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    void signUp(CustomUserDetails customUserDetails);

    /**
     * Login 전 이용자가 입력한 ID 기반 회원 정보를 가져오기 위한 추상 Method
     * @param username - 로그인 시 이용자가 입력한 ID
     * @return CostomUserDetails
     * @see ""
     */

    Optional<CustomUserDetails> getUserByID(String username);

    /**
     * Login 시 이용자가 가진 고유 번호와 권한 기반으로 만들어진 Refresh Token DB 저장을 위한 추상 Method
     * @param refreshToken - Refresh Token
     * @param username - Login 요청 이용자 ID
     * @see ""
     */

    void setRefreshToken(String refreshToken, String username);

    /**
     * Access Token 재 발급 시 회원 고유 번호를 통해 존재하는 회원인지 DB에서 검사하는 Method
     * @param userPk - 회원 고유 번호
     * @see ""
     */

    Optional<CustomUserDetails> getUserByPK(Integer userPk);

    /**
     * Logout 전 유효한 Refresh Token인지 확인하는 Method
     * @param refreshToken - Logout 요청 회원 보유 Refresh Token
     * @see ""
     */

    void refreshTokenDelete(String refreshToken);
} // interface 끝
