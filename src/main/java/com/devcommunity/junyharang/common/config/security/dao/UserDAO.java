package com.devcommunity.junyharang.common.config.security.dao;

import com.devcommunity.junyharang.mapper.user.UserMapper;
import com.devcommunity.junyharang.model.vo.member.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 회원 관련 DAO
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.29 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.29 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

@Repository @RequiredArgsConstructor @Slf4j
public class UserDAO {

    private static final Logger Logger = LoggerFactory.getLogger(UserDAO.class);

    private final UserMapper userMapper;

    /**
     * Login 이용자가 입력한 아이디로 유저 정보를 얻어 오는 Method
     * @param username  이용자가 입력한 ID 값
     * @return {@link CustomUserDetails}    DB에서 찾은 이용자 정보
     */

    public Optional<CustomUserDetails> getUserByID(String username) {

        log.info("UserDAO의 getUserByID(String username)가 호출 되었습니다!");
        log.info("userMapper.getUserByID(username)을 전달하여 DB를 통해 회원 정보를 요청 하겠습니다!");

        Optional<CustomUserDetails> userByID = userMapper.getUserByID(username);

        return userByID;

    } // getUserByID(String username) 끝

    public void setRefreshToken(String token, String username) {

        log.info("UserDAO의 getUserByID(String username)가 호출 되었습니다!");
        log.info("userMapper.setRefreshToken(token)을 전달하여 DB를 통해 Refresh Token을 저장 하겠습니다!");

        userMapper.setRefreshToken(token, username);
    }
} // class 끝
