package com.devcommunity.junyharang.mapper.user;

import com.devcommunity.junyharang.model.dto.user.DuplicateByUserEmailDTO;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserIDDTO;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserNickNameDTO;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserPhoneNumberDTO;
import com.devcommunity.junyharang.model.vo.member.CustomUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 회원 관련 Mapper
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
} // interface 끝
