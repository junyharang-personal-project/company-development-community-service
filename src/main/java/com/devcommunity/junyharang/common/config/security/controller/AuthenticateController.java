package com.devcommunity.junyharang.common.config.security.controller;

import com.devcommunity.junyharang.common.config.security.common.jwt.JWTFilter;
import com.devcommunity.junyharang.common.config.security.common.jwt.TokenProvider;
import com.devcommunity.junyharang.common.config.security.dao.UserDAO;
import com.devcommunity.junyharang.common.config.security.dto.request.SignInRequestDTO;
import com.devcommunity.junyharang.common.config.security.dto.TokenDTO;
import com.devcommunity.junyharang.common.config.security.service.AuthService;
import com.devcommunity.junyharang.common.config.security.service.CustomUserDetailService;
import com.devcommunity.junyharang.common.constant.DefaultResponse;
import com.devcommunity.junyharang.common.constant.ServiceURIMng;
import com.devcommunity.junyharang.common.constant.SwaggerApiInfo;
import com.devcommunity.junyharang.model.vo.member.CustomUserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Login & Logout API
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

@Api(tags = {"회원 인증 관련 API"}) @Slf4j @RequiredArgsConstructor
@RequestMapping(ServiceURIMng.USER)
@RestController public class AuthenticateController {

    private final AuthService authService;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final TokenProvider tokenProvider;

    @ApiOperation(value = SwaggerApiInfo.SIGN_IN, notes = "Login 서비스 입니다.")
    @ApiParam(name = "signInRequestDTO", value = "로그인 하고자 하는 이용자 ID, Password", readOnly = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login 성공"),
            @ApiResponse(code = 401, message = "인증 필요"),
            @ApiResponse(code = 403, message = "접근 권한 없음"),
            @ApiResponse(code = 500, message = "Server Internal Error")
    })

    @PostMapping("/signin") public ResponseEntity<DefaultResponse> signIn(@Valid @RequestBody SignInRequestDTO signInRequestDTO) {

        log.info("AuthenticateController의 signin(@RequestBody String user)이 호출 되었습니다!");
        log.info("authService.authorize(signInRequestDTO.getUsername(), signInRequestDTO.getPassword()) 호출 하며, 반환 값을 Response 하겠습니!");

        return new ResponseEntity<>(authService.signIn(signInRequestDTO), HttpStatus.OK);

    } // signin(@RequestBody String user) 끝


//    @ApiOperation(value = SwaggerApiInfo.REPLACE_TOKEN, notes = "JWT 만료 시 재 발행 서비스 입니다.")
//    @ApiParam(name = "tokenDTO", value = "이용자가 가지고 있는 Token 정보를 요청으로 주어야 합니다.", readOnly = true)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "재 발행 성공"),
//            @ApiResponse(code = 500, message = "Server Internal Error")
//    })
//
//    @PostMapping("/reissue") public ResponseEntity<TokenDTO> jwtReissue(HttpServletRequest request) {
//
//        log.info("AuthenticateController의 jwtReissue(@Valid @RequestBody TokenDTO requestTokenDTO)가 호출 되었습니다!");
//
//        authService.jwtReissue(request)
//
//        return new ResponseEntity<>(authService.jwtReissue(requestTokenDTO.getAccessToken(), requestTokenDTO.getRefreshToken()), HttpStatus.OK);
//
//
//    }

    @ApiOperation(value = SwaggerApiInfo.SIGN_OUT, notes = "Logout 서비스 입니다.")
    @ApiParam(name = "user", value = "Token과 회원 정보를 넘겨주어야 합니다.", readOnly = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Logout 성공"),
            @ApiResponse(code = 500, message = "Server Internal Error")
    })

    @RequestMapping("/logout") public boolean logout(@RequestBody String token) {

        log.info("AuthenticateController의 logout(@RequestBody String token)이 호출 되었습니다!");

        return true;

    } // logout(@RequestBody String token) 끝

} // class 끝
