package com.devcommunity.junyharang.mapper.user;

import com.devcommunity.junyharang.model.dto.user.DuplicateByIdInfoDTO;
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
     * 회원 가입 전 등록된 정보 인지 확인
     * @param duplicateByIdInfoDTO - 회원 가입 전 등록 되어 있는 정보 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    Optional<DuplicateByIdInfoDTO> findByUserID(DuplicateByIdInfoDTO duplicateByIdInfoDTO);

    /**
     * 회원 가입 전 등록된 정보 인지 확인 - 별명
     * @param duplicateByIdInfoDTO - 회원 가입 전 등록 되어 있는 정보 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    Optional<DuplicateByIdInfoDTO> findByUserNickName(DuplicateByIdInfoDTO duplicateByIdInfoDTO);

    /**
     * 회원 가입 전 등록된 정보 인지 확인 - E-mail
     * @param duplicateByIdInfoDTO - 회원 가입 전 등록 되어 있는 정보 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    Optional<DuplicateByIdInfoDTO> findByUserEmail(DuplicateByIdInfoDTO duplicateByIdInfoDTO);

    /**
     * 회원 가입 전 등록된 정보 인지 확인 - 핸드폰 번호
     * @param duplicateByIdInfoDTO - 회원 가입 전 등록 되어 있는 정보 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    Optional<DuplicateByIdInfoDTO> findByUserPhoneNumber(DuplicateByIdInfoDTO duplicateByIdInfoDTO);

    /**
     * 회원 가입을 위한 추상 Method
     * @param customUserDetails - 회원 가입 시 이용자가 입력한 정보 담은 객체
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    void signUp(CustomUserDetails customUserDetails);
} // interface 끝
