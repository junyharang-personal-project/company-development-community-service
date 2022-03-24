package com.devcommunity.junyharang.mapper.user;

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
     * 회원 가입 전 등록된 계정인지 확인
     * @param userId - 회원 가입 전 등록되어 있는 ID인지 확인을 위한 이용자 입력 ID
     * @return CustomUserDetails - 해당 회원의 정보 반
     * @see ""
     */

    Optional<CustomUserDetails> findByUserName(String userId);

    /**
     * 회원 가입을 위한 추상 Method
     * @param customUserDetails - 회원 가입 시 이용자가 입력한 정보 담은 객체
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    void signUp(CustomUserDetails customUserDetails);
} // interface 끝
