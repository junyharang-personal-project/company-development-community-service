package com.devcommunity.junyharang.common.config.security.service;

import com.devcommunity.junyharang.common.config.security.dao.UserDAO;
import com.devcommunity.junyharang.common.config.security.dto.TokenDTO;
import com.devcommunity.junyharang.model.vo.member.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor @Slf4j
@Service public class CustomUserDetailService implements UserDetailsService {

    private final UserDAO userDAO;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("UserDetailsService를 구현한 CustomUserDetailService의 loadUserByUsername(String username)이 동작 하였습니다!");
        log.info("DB를 통해 이용자가 입력한 ID를 기반으로 회원 정보를 가져 오겠습니다! 매개 변수 값 : " + username);

        CustomUserDetails userInfo = userDAO.getUserByID(username);

        if (userInfo == null) {
            log.info("로그인 요청 이용자가 입력한 ID로 DB에서 검색을 했지만, 해당 회원은 존재 하지 않습니다!");

            throw new UsernameNotFoundException("존재하지 않는 회원 입니다!");
        }   // if (userInfo == null) 끝

        log.info("로그인 요청 이용자가 입력한 ID로 DB에서 해당 회원 정보를 찾았습니다! 해당 내용 반환하겠습니다!");

        return userInfo;

    } // loadUserByUsername(String username) 끝
} // class 끝
