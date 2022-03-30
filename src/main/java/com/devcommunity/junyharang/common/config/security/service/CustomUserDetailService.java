package com.devcommunity.junyharang.common.config.security.service;

import com.devcommunity.junyharang.common.config.security.dao.UserDAO;
import com.devcommunity.junyharang.model.vo.member.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 회원 정보 관리를 위한 Class
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

@RequiredArgsConstructor @Slf4j
@Component("userDetailService") public class CustomUserDetailService implements UserDetailsService {

    private final UserDAO userDAO;

    @Override @Transactional
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("UserDetailsService를 구현한 CustomUserDetailService의 loadUserByUsername(String username)이 동작 하였습니다!");
        log.info("DB를 통해 이용자가 입력한 ID를 기반으로 회원 정보를 가져 오겠습니다! 매개 변수 값 : " + username);

        Optional<CustomUserDetails> userInfo = userDAO.getUserByID(username);

        if (userInfo.isEmpty()) {

            log.info("이용자가 입력한 ID에 해당하는 회원 정보가 존재하지 않습니다!");
            throw new UsernameNotFoundException("존재하지 않는 회원 정보 입니다!");

        }   // if (userInfo.isEmpty()) 끝

        return userInfo.get();

    } // loadUserByUsername(String username) 끝
} // class 끝
