package com.devcommunity.junyharang.service.user;

import com.devcommunity.junyharang.common.constant.DefaultResponse;
import com.devcommunity.junyharang.mapper.user.UserMapper;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserEmailDTO;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserIDDTO;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserNickNameDTO;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserPhoneNumberDTO;
import com.devcommunity.junyharang.model.vo.member.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

@RequiredArgsConstructor @Slf4j
@Service public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    /**
     * 회원 가입 전 등록된 ID 정보 인지 확인
     * @param duplicateByUserIDDTO - 회원 가입 전 등록 되어 있는 ID 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    @Override
    public DefaultResponse duplicateUserID(DuplicateByUserIDDTO duplicateByUserIDDTO) {

        log.info("UserServiceImpl의 duplicateUserID(String username)가 호출 되었습니다! Client로 부터 전달된 값 : " + duplicateByUserIDDTO);
        log.info("DB에서 회원 가입 요청 이용자가 입력한 UserId(username)를 통해 해당 회원이 등록 되어 있는지 찾겠습니다!");
        Optional<DuplicateByUserNickNameDTO> findByUserID = userMapper.findByUserID(duplicateByUserIDDTO);

        if (findByUserID.isEmpty()) {

            log.info("중복 되는 값이 없습니다! 200 Code와 함께 \"사용 가능!\" 반환 하겠습니다!");

            return DefaultResponse.response(200, "사용 가능", "OK", findByUserID);

        } else {

            log.info("이미 존재하는 ID 입니다! 409 Code와 함께 \"이미 존재하는 값 입니다!\" 반환 하겠습니다!");

        return DefaultResponse.response(409, "이미 존재하는 ID 입니다!", "Conflict");
        } // // if-else (checkEmail.isEmpty()) 끝

    } // duplicateId(String username) 끝

    /**
     * 회원 가입 전 등록된 별명 정보 인지 확인
     * @param duplicateByUserNickNameDTO - 회원 가입 전 등록 되어 있는 별명 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    @Override
    public DefaultResponse duplicateUserNickName(DuplicateByUserNickNameDTO duplicateByUserNickNameDTO) {
        log.info("UserServiceImpl의 duplicateUserID(String username)가 호출 되었습니다! Client로 부터 전달된 값 : " + duplicateByUserNickNameDTO);
        log.info("DB에서 회원 가입 요청 이용자가 입력한 별명을 통해 해당 회원이 등록 되어 있는지 찾겠습니다!");
        Optional<DuplicateByUserNickNameDTO> findByUserNickName = userMapper.findByUserNickName(duplicateByUserNickNameDTO);



        if (findByUserNickName.isEmpty()) {

            log.info("중복 되는 값이 없습니다! 200 Code와 함께 \"사용 가능!\" 반환 하겠습니다!");

            return DefaultResponse.response(200, "사용 가능", "OK", findByUserNickName);

        } else {

            log.info("이미 존재하는 별명 입니다! 409 Code와 함께 \"이미 존재하는 값 입니다!\" 반환 하겠습니다!");

            return DefaultResponse.response(409, "이미 존재하는 별명 입니다!", "Conflict");
        } // // if-else (checkEmail.isEmpty()) 끝
    }

    /**
     * 회원 가입 전 등록된 Email 정보 인지 확인
     * @param duplicateByUserEmailDTO - 회원 가입 전 등록 되어 있는 EMAIL 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    @Override
    public DefaultResponse duplicateUserEmail(DuplicateByUserEmailDTO duplicateByUserEmailDTO) {
        log.info("UserServiceImpl의 duplicateUserID(String username)가 호출 되었습니다! Client로 부터 전달된 값 : " + duplicateByUserEmailDTO);
        log.info("DB에서 회원 가입 요청 이용자가 입력한 UserId(username)를 통해 해당 회원이 등록 되어 있는지 찾겠습니다!");
        Optional<DuplicateByUserNickNameDTO> findByUserEmail = userMapper.findByUserEmail(duplicateByUserEmailDTO);

        if (findByUserEmail.isEmpty()) {

            log.info("중복 되는 값이 없습니다! 200 Code와 함께 \"사용 가능!\" 반환 하겠습니다!");

            return DefaultResponse.response(200, "사용 가능", "OK", findByUserEmail);

        } else {

            log.info("이미 존재하는 Email 입니다! 409 Code와 함께 \"이미 존재하는 값 입니다!\" 반환 하겠습니다!");

            return DefaultResponse.response(409, "이미 존재하는 Email 입니다!", "Conflict");
        } // // if-else (checkEmail.isEmpty()) 끝
    }

    /**
     * 회원 가입 전 등록된 핸드폰 번호 정보 인지 확인
     * @param duplicateByUserPhoneNumberDTO - 회원 가입 전 등록 되어 있는 핸드폰 번호 인지 확인을 위한 이용자 입력 정보 DTO
     * @return CustomUserDetails - 해당 회원의 정보 반환
     * @see ""
     */

    @Override
    public DefaultResponse duplicateUserPhoneNumber(DuplicateByUserPhoneNumberDTO duplicateByUserPhoneNumberDTO) {
        log.info("UserServiceImpl의 duplicateUserID(String username)가 호출 되었습니다! Client로 부터 전달된 값 : " + duplicateByUserPhoneNumberDTO);
        log.info("DB에서 회원 가입 요청 이용자가 입력한 UserId(username)를 통해 해당 회원이 등록 되어 있는지 찾겠습니다!");
        Optional<DuplicateByUserNickNameDTO> findByUserphoneNumber = userMapper.findByUserPhoneNumber(duplicateByUserPhoneNumberDTO);

        if (findByUserphoneNumber.isEmpty()) {

            log.info("중복 되는 값이 없습니다! 200 Code와 함께 \"사용 가능!\" 반환 하겠습니다!");

            return DefaultResponse.response(200, "사용 가능", "OK", findByUserphoneNumber);

        } else {

            log.info("이미 존재하는 핸드폰 번호 입니다! 409 Code와 함께 \"이미 존재하는 값 입니다!\" 반환 하겠습니다!");

            return DefaultResponse.response(409, "이미 존재하는 핸드폰 번호 입니다!", "Conflict");
        } // // if-else (checkEmail.isEmpty()) 끝
    }

    /**
     * 회원 가입 서비스
     * @param customUserDetails - 회원 가입을 위한 이용자 입력값을 담은 Object
     * @return DefaultResponse - 서버 처리 여부에 해당하는 Status Code 반환을 위한 객체
     * @see ""
     */

    @Override
    public DefaultResponse signUp(CustomUserDetails customUserDetails) {

        log.info("UserServiceImpl의 signUp(CustomUserDetails customUserDetails)이 호출 되었습니다!");

        log.info("DB에서 회원 가입 요청 이용자가 입력한 UserId(username)를 통해 해당 회원이 등록 되어 있는지 찾겠습니다!");

        log.info("이용자의 패스워드를 암호화 하겠습니다!");
        customUserDetails.setPassword(passwordEncoder.encode(customUserDetails.getPassword()));

        userMapper.signUp(customUserDetails);

        return DefaultResponse.response(200, "회원 가입 완료!", "Success Sign up");
    } // signUp(CustomUserDetails customUserDetails) 끝
} // class 끝
