package com.devcommunity.junyharang.common.config.security.controller;

import com.devcommunity.junyharang.common.config.security.dto.TokenDTO;
import com.devcommunity.junyharang.common.config.security.dto.request.SignInRequestDTO;
import com.devcommunity.junyharang.common.config.security.service.AuthService;
import com.devcommunity.junyharang.common.constant.DefaultResponse;
import com.devcommunity.junyharang.common.constant.ServiceURIMng;
import com.devcommunity.junyharang.common.constant.SwaggerApiInfo;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Login & Logout API
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.29 최초 작성
 *    주니하랑, 1.1.0, 2022.03.31 JWT(Access Token, Refresh Token) 기반 로그인 기능 재 구성
 *    주니하랑, 1.1.1, 2022.03.31 Access Token 재 발급 기능 구현 완료
 * </pre>
 *
 * @author 주니하랑
 * @version 1.1.1, 2022.03.31 Access Token 재 발급 기능 구현 완료
 * @See ""
 * @see <a href=""></a>
 */

@Api(tags = {"회원 인증 관련 API"}) @Slf4j @RequiredArgsConstructor
@RequestMapping(ServiceURIMng.USER)
@RestController public class AuthenticateController {

    private final AuthService authService;


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
        log.info("authService.authorize(signInRequestDTO.getUsername(), signInRequestDTO.getPassword()) 호출 하며, 반환 값을 Response 하겠습니다!");

        return new ResponseEntity<>(authService.signIn(signInRequestDTO), HttpStatus.OK);

    } // signin(@RequestBody String user) 끝


    @ApiOperation(value = SwaggerApiInfo.REPLACE_TOKEN, notes = "JWT 만료 시 Access Token 재 발행 서비스 입니다.")
    @ApiParam(name = "request", value = "이용자가 가지고 있는 Refresh Token 정보를 요청으로 주어야 합니다.", readOnly = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "토큰 재 발행 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 401, message = "토큰 불일치"),
            @ApiResponse(code = 500, message = "Server Internal Error")
    })

    @PostMapping("/reissue") public ResponseEntity<DefaultResponse> jwtReissue(HttpServletRequest request) {

        log.info("AuthenticateController의 jwtReissue(HttpServletRequest request)가 호출 되었습니다!");

        DefaultResponse jwtReissueResponse = authService.jwtReissue(request);

        log.info("요청으로 들어온 Refresh Token이 정상인지 확인 하겠습니다!");

        if (jwtReissueResponse.getStatusCode() == HttpStatus.UNAUTHORIZED.value()) {

            log.info("Refresh Token이 일치하지 않습니다!");

            return new ResponseEntity<>(jwtReissueResponse, HttpStatus.UNAUTHORIZED);

        } else {

            log.info("Refresh Token 검사가 정상 처리 되었습니다! Access Token을 재 발급 하였으며, 응답 해 주겠습니다!");

            return new ResponseEntity<>(jwtReissueResponse, HttpStatus.OK);

        } // if (jwtReissueResponse.getStatusCode() == HttpStatus.UNAUTHORIZED.value()) 끝
    } // jwtReissue(HttpServletRequest request) 끝

    @ApiOperation(value = SwaggerApiInfo.SIGN_OUT, notes = "Logout 서비스 입니다.")
    @ApiParam(name = "user", value = "Token과 회원 정보를 넘겨주어야 합니다.", readOnly = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Logout 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 401, message = "토큰 불일치"),
            @ApiResponse(code = 500, message = "Server Internal Error")
    })

    @DeleteMapping("/logout") public ResponseEntity<DefaultResponse> logout(HttpServletRequest request) {

        log.info("AuthenticateController의 logout(HttpServletRequest request)이 호출 되었습니다!");

        DefaultResponse logoutResponse = authService.logout(request);

        log.info("요청으로 들어온 Refresh Token이 정상인지 확인 하겠습니다!");

        if (logoutResponse.getStatusCode() == HttpStatus.UNAUTHORIZED.value()) {

            log.info("Refresh Token이 일치하지 않습니다!");

            return new ResponseEntity<>(logoutResponse, HttpStatus.UNAUTHORIZED);

        } else {

            log.info("Refresh Token 검사가 정상 처리 되었습니다! Logout 처리를 하였으며, 응답 해 주겠습니다!");

            return new ResponseEntity<>(logoutResponse, HttpStatus.OK);

        }   // if (logoutResponse.getStatusCode() == HttpStatus.UNAUTHORIZED.value()) - else 끝
    } // logout(@RequestBody String token) 끝

} // class 끝
