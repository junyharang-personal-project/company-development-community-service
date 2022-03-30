package com.devcommunity.junyharang.common.config.security.service;

import com.devcommunity.junyharang.common.config.security.common.jwt.JWTAuthenticationToken;
import com.devcommunity.junyharang.common.config.security.common.jwt.TokenProvider;
import com.devcommunity.junyharang.common.config.security.dao.UserDAO;
import com.devcommunity.junyharang.common.config.security.dto.TokenDTO;
import com.devcommunity.junyharang.common.config.security.dto.request.SignInRequestDTO;
import com.devcommunity.junyharang.common.config.security.dto.response.SignInResponseDTO;
import com.devcommunity.junyharang.common.constant.DefaultResponse;
import com.devcommunity.junyharang.common.exception.UnauthorizedException;
import com.devcommunity.junyharang.model.vo.member.CustomUserDetails;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpRetryException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 회원 인증 관련 비즈니스 로직 담당 Class
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.30 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.30 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

@Transactional @RequiredArgsConstructor @Slf4j
@Service public class AuthServiceImpl implements AuthService{

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserDAO userDAO;

    /**
     * Login 관련 Method
     * @param username - 로그인 요청 이용자 ID
     * @param password - 로그인 요청 이용자 비민번호
     * @return TokenDTO - JWT(Access Token, Refresh Token) 반환
     * @see "https://wnwngus.tistory.com/65"
     */

    @Override
    public DefaultResponse<SignInResponseDTO> authorize(SignInRequestDTO signInRequestDTO) {

        log.info("AuthService를 구현한 AuthServiceImpl의 authorize(String username, String password)가 동작 하였습니다!");

        Optional<CustomUserDetails> userInfo = userDAO.getUserByID(signInRequestDTO.getUsername());

        return userInfo.map(username -> {

            userDAO.

        })
    }

    private String getAuthorities(Authentication authentication) {  // 권한 가져오기 Method

        log.info("AuthService를 구현한 AuthServiceImpl의 getAuthorities(Authentication authentication)가 동작 하였습니다!");
        log.info("이용자 권한 추출 작업 시작 하겠습니다!");

        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

    } // getAuthorities(Authentication authentication) 끝


    /**
     * JWT 재 발급 관련 Method
     * @param request - JWT 요청 이용자가 재발급을 위해 제출한 요청 객체
     * @return DefaultResponse - Http Status Code 관련 반환 객체
     * @see ""
     */
    @Override
    public DefaultResponse jwtReissue(HttpServletRequest request) {

        log.info("AuthService를 구현한 AuthServiceImpl의 jwtReissue(String acessToken, String refreshToken)가 동작 하였습니다!");
        log.info("JWT 재 발급 요청 이용자가 보낸 요청 Header에 \"Authorization\" 값을 jwt 변수에 담겠습니다!");

        String jwt = request.getHeader("Authorization");

        if (jwt == null) {
            log.info("JWT 재 발급 요청 이용자가 보낸 요청 Header에 \"Authorization\" 값이 없습니다!");

            return DefaultResponse.response(HttpStatus.UNAUTHORIZED.value(), "요청 Header에 JWT가 담겨 있지 않습니다!", "Token Error");
        } // if (jwt == null) 끝

        log.info("Request Header Authorization에서 \"Bearer \"를 제외한 값을 추출하여 token 변수에 담겠습니다!");

        String token = jwt.substring("Bearer ".length());

        log.info("JWT에서 Claim 추출하기 위해 TokenProvider.getClaims(token)를 호출하겠습니다!");

        Claims claims = TokenProvider.getClaims(token);

        log.info("claim이 null인지 확인 하겠습니다!");

        if (claims == null) {

            log.info("claim이 Null 입니다! Token이 일치하지 않습니다!");

            return DefaultResponse.response(HttpStatus.UNAUTHORIZED.value(), "토큰 불 일치", "Token Inconsistency");
        } // if (claims == null) 끝

        String authoritiesKey = claims.get("AUTHORITIES_KEY", String.class);

        if (authoritiesKey.equals(TokenProvider.))

        }


    } // class 끝
