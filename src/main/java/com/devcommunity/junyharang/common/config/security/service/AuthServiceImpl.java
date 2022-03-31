package com.devcommunity.junyharang.common.config.security.service;

import com.devcommunity.junyharang.common.config.security.common.jwt.TokenProvider;
import com.devcommunity.junyharang.common.config.security.dao.UserDAO;
import com.devcommunity.junyharang.common.config.security.dto.request.SignInRequestDTO;
import com.devcommunity.junyharang.common.config.security.dto.response.ReplaceTokenResponseDTO;
import com.devcommunity.junyharang.common.config.security.dto.response.SignInResponseDTO;
import com.devcommunity.junyharang.common.constant.DefaultResponse;
import com.devcommunity.junyharang.common.constant.ResponseCode;
import com.devcommunity.junyharang.model.vo.member.CustomUserDetails;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 회원 인증 관련 비즈니스 로직 담당 Class
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.30 최초 작성
 *    주니하랑, 1.1.0, 2022.03.31 JWT(Access Token, Refresh Token) 기반 로그인 기능 재 구성
 *    주니하랑, 1.1.1, 2022.03.31 Access Token 재 발급 기능 구현 완료
 * </pre>
 *
 * @author 주니하랑
 * @version 1.1.1, 2022.03.31 Access Token 재 발급 기능 구현 완료
 * @See ""
 * @see <a href=""></a>
 */

@Transactional @RequiredArgsConstructor @Slf4j
@Service public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserDAO userDAO;

    /**
     * Login 관련 Method
     *
     * @param signInRequestDTO - 로그인 요청 이용자 ID와 Password가 들어 있는 객체
     * @return DefaultResponse<SignInResponseDTO> - Http Status 관련 응답과 로그인 시 반환될 내용을 가진 객체
     * @see ""
     */

    @Override
    public DefaultResponse<SignInResponseDTO> signIn(SignInRequestDTO signInRequestDTO) {

        log.info("AuthService를 구현한 AuthServiceImpl의 authorize(String username, String password)가 동작 하였습니다!");
        log.info("로그인 요청 이용자 ID 값 : " + signInRequestDTO.getUsername() + ", 패스워드 값 : " + signInRequestDTO.getPassword());
        log.info("DB에서 이용자가 입력한 ID를 통해 존재하는 회원이 있는지 찾아 보겠습니다!");

        Optional<CustomUserDetails> userInfoByID = userDAO.getUserByID(signInRequestDTO.getUsername());

        if (userInfoByID.isPresent()) {

            log.info("로그인 요청 이용자가 입력한 ID가 DB 값과 일치 합니다!");
            log.info("로그인 요청 이용자가 입력한 Password가 DB의 값과 일치하는지 확인 하겠습니다!");

            if (passwordEncoder.matches(signInRequestDTO.getPassword(), userInfoByID.get().getPassword())) {

                log.info("로그인 요청 이용자가 입력한 패스워드가가 DB 값과 일치 합니다!");
                log.info("로그인 요청 이용자 정보가 존재합니다! 해당 이용자의 고유번호와 회원 역할을 통해 Access Token과 Refresh Token을 생성하겠습니다!");

                return userInfoByID.map(user -> {

                    String accessToken = TokenProvider.createAccessToken(userInfoByID.get().getUserId(), userInfoByID.get().getAuthority());

                    String refreshToken = TokenProvider.createRefreshToken(userInfoByID.get().getUserId(), userInfoByID.get().getAuthority());

                    log.info("JWT(Access Token, Refresh Token)이 모두 생성되었으며, Refresh Token은 회원 Table에 저장해 두겠습니다!");

                    userDAO.setRefreshToken(refreshToken, userInfoByID.get().getUsername());

//                    user.setRefreshToken(refreshToken);

                    log.info("Login이 성공 하였습니다! 200 Code와 함께 \"성공\" 과 \"Sucess\"와 로그인 요청 이용자의 고유 번호, 회원 역할, Access Token, RefreshToken 반환 하겠습니다!");

                    return DefaultResponse.response(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessageKo(), ResponseCode.SUCCESS.getMessageEn(), new SignInResponseDTO(accessToken, refreshToken, userInfoByID.get().getUserId(), userInfoByID.get().getAuthority(), userInfoByID.get().getNickname()));

                }).orElseGet(() -> DefaultResponse.response(ResponseCode.CHECK_VALUE.getCode(), "계정을 확인 해 주세요!", "Check your Identity Value"));

            } // if (passwordEncoder.matches(signInRequestDTO.getPassword(), userInfoByID.get().getPassword())) 끝

            log.info("Login이 실패 하였습니다! 200 Code와 함께 \"Password가 일치 하지 않습니다!\" 와 \"Sucess\"를 반환 하겠습니다!");

            return DefaultResponse.response(ResponseCode.CHECK_VALUE.getCode(), "Password가 일치하지 않습니다!", ResponseCode.CHECK_VALUE.getMessageEn());

        } // if (userInfoByID.isPresent())

        log.info("Login이 실패 하였습니다! 200 Code와 함께 \"ID가 일치 하지 않습니다!\" 와 \"Sucess\"를 반환 하겠습니다!");

        return DefaultResponse.response(ResponseCode.CHECK_VALUE.getCode(), "ID가 일치 하지 않습니다!", ResponseCode.SUCCESS.getMessageEn());

    }   // signIn(SignInRequestDTO signInRequestDTO) 끝

    private String getAuthorities(Authentication authentication) {  // 권한 가져오기 Method

        log.info("AuthService를 구현한 AuthServiceImpl의 getAuthorities(Authentication authentication)가 동작 하였습니다!");
        log.info("이용자 권한 추출 작업 시작 하겠습니다!");

        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

    } // getAuthorities(Authentication authentication) 끝


    /**
     * JWT 재 발급 관련 Method
     *
     * @param request - JWT 요청 이용자가 재발급을 위해 제출한 요청 객체
     * @return DefaultResponse - Http Status Code 관련 반환 객체
     * @see ""
     */
    @Override
    public DefaultResponse jwtReissue(HttpServletRequest request) {

        log.info("AuthService를 구현한 AuthServiceImpl의 jwtReissue(String acessToken, String refreshToken)가 동작 하였습니다!");
        log.info("JWT 재 발급 요청 이용자가 보낸 요청 Header에 \"Authorization\" 값을 jwt 변수에 담겠습니다!");

        String jwt = request.getHeader("Authorization");

        log.info("Request Header에 JWT가 담겨 있었는지 확인 하겠습니다!");

        if (jwt == null) {

            log.info("Request Header에 JWT가 담겨 있지 않았습니다!");

            return DefaultResponse.response(HttpStatus.BAD_REQUEST.value(), "잘못 된 요청", "Bad Request");

        } // if (jwt == null) 끝

        log.info("Request Header에 JWT가 담겨 있으며, \"Bearer \" 문자를 제외한 값을 jwtToken 변수에 담겠습니다!");

        String jwtToken = jwt.substring("Bearer ".length());

        Claims claims = TokenProvider.getClaims(jwtToken);

        if (claims == null) {

            log.info("요청으로 전달 된 Refresh Token과 Server에 저장된 Refresh Token이 일치하지 않습니다!");

            return DefaultResponse.response(HttpStatus.UNAUTHORIZED.value(), "토큰 불일치", "Token Inconsistency");

        } // if (claims == null) 끝

        String tokenName = claims.get("token_name", String.class);
        String userRole = claims.get("user_role", String.class);

        if (tokenName.equals(TokenProvider.REFRESH_TOKEN_NAME)) {

            log.info("Refresh Token의 이름이 생성 당시 Refresh Token 이름과 일치 합니다!");

            Integer userPk = claims.get("user_pk", Integer.class);

            Boolean check = refreshTokenCheck(userPk, jwtToken);

            if (check) {

                String accessToken;

                log.info("요청으로 들어온 Refresh Token이 확인 되었습니다! Access Token 생성하겠습니다!");

                accessToken = TokenProvider.createAccessToken(userPk, userRole);

                return DefaultResponse.response(HttpStatus.OK.value(), "토큰 재 발행 성공", "Access Token Reissue Success", new ReplaceTokenResponseDTO(accessToken));

            } else {

                return DefaultResponse.response(HttpStatus.UNAUTHORIZED.value(), "토큰 불일치", "Token Inconsistency");

            } // if (check) - else 끝

        }   // if (tokenName.equals(TokenProvider.REFRESH_TOKEN_NAME)) 끝

        return DefaultResponse.response(HttpStatus.UNAUTHORIZED.value(), "토큰 불일치", "Token Inconsistency");

    }   // jwtReissue(HttpServletRequest request) 끝

    public Boolean refreshTokenCheck(Integer userPk, String refreshToken) {

        log.info("AuthService를 구현한 AuthServiceImpl의 refreshTokenCheck(Integer userPk, String jwtToken)가 동작 하였습니다!");

        Optional<CustomUserDetails> userInfo = userDAO.getUserByPK(userPk);

        return userInfo.map(user -> {

            if (user.getToken().equals(refreshToken)) {

                log.info("요청으로 들어온 Refresh Token과 DB에 저장되어 있는 Refresh Token이 일치 합니다!");

                return true;

            } else {

                log.info("요청으로 들어온 Refresh Token과 DB에 저장되어 있는 Refresh Token이 일치하지 않습니다!");

                return false;

            } // if (user.getToken().equals(refreshToken)) - else 끝
        }).orElse(false);
    } // refreshTokenCheck(Integer userPk, String jwtToken) 끝

} // class 끝
