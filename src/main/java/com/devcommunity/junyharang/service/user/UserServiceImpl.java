package com.devcommunity.junyharang.service.user;

import com.devcommunity.junyharang.common.constant.DefaultResponse;
import com.devcommunity.junyharang.mapper.user.UserMapper;
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
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.24 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

@RequiredArgsConstructor @Slf4j
@Service public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    /**
     * 회원 가입 서비스
     * @param customUserDetails - 회원 가입을 위한 이용자 입력값을 담은 Object
     * @return DefaultResponse - 서버 처리 여부에 해당하는 Status Code 반환을 위한 객체
     * @see ""
     */

    @Override
    public DefaultResponse signUp(CustomUserDetails customUserDetails) {

        log.info("UserServiceImpl의 signUp(CustomUserDetails customUserDetails)이 호출 되었습니다!");

        log.info("DB에서 로그인 요청 이용자가 입력한 UserId(username)를 통해 해당 회원이 등록 되어 있는지 찾겠습니다!");

        String userID = customUserDetails.getUsername();

        Optional<CustomUserDetails> checkEmail = userMapper.findByUserName(userID);

        if (checkEmail.isEmpty()) {

            log.info("회원 가입 요청 이용자가 입력한 Email 주소가 DB에 저장 되어 있지 않습니다!");
            log.info("회원 가입 입력란 중 중복 불가한 값에 대해 중복 값이 있는지 확인 하겠습니다!");

            return checkEmail.filter(user -> user.getUserEmail().equals(customUserDetails.getUserEmail()))
                    .map(user -> {
                        log.info("이미 존재하는 E-mail 주소 입니다! 200 Code와 함께 \"이미 존재하는 값 입니다!\" 반환 하겠습니다!");

                        return DefaultResponse.response(200, "이미 존재하는 값 입니다!", "The value already existed.");

                    }).orElse((checkEmail.filter(user -> user.getNickname().equals(customUserDetails.getNickname())))
                            .map(user -> {

                                log.info("이미 존재하는 별명 입니다! 200 Code와 함께 \"이미 존재하는 값 입니다!\" 반환 하겠습니다!");

                                return DefaultResponse.response(200, "이미 존재하는 값 입니다!", "The value already existed.");

                            }).orElse(((checkEmail.filter(user -> user.getUserPhone().equals(customUserDetails.getUserPhone()))))
                                    .map(user -> {

                                        log.info("이미 존재하는 핸드폰 번호 입니다! 200 Code와 함께 \"이미 존재하는 값 입니다!\" 반환 하겠습니다!");

                                        return DefaultResponse.response(200, "이미 존재하는 값 입니다!", "The value already existed.");

                                    }).orElseGet(() -> {

                                        log.info("중복된 내용이 없으므로, DB에 저장 하겠습니다!");

                                        log.info("이용자의 패스워드를 암호화 하겠습니다!");
                                        customUserDetails.setPassword(passwordEncoder.encode(customUserDetails.getPassword()));

                                        userMapper.signUp(customUserDetails);

                                        return DefaultResponse.response(200, "회원 가입 완료!", "Success Sign up");

                                    })));
        } else {

            log.info("이미 존재하는 ID 입니다! 200 Code와 함께 \"이미 존재하는 값 입니다!\" 반환 하겠습니다!");

            return DefaultResponse.response(200, "이미 존재하는 값 입니다!", "The value already existed.");
        } // // if-else (checkEmail.isEmpty()) 끝
    } // signUp(CustomUserDetails customUserDetails) 끝
} // class 끝
