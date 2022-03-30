package com.devcommunity.junyharang.common.config.security.controller;

import com.devcommunity.junyharang.common.config.security.common.jwt.JWTFilter;
import com.devcommunity.junyharang.common.config.security.common.jwt.TokenProvider;
import com.devcommunity.junyharang.common.config.security.dao.UserDAO;
import com.devcommunity.junyharang.common.config.security.dto.request.SignInRequestDTO;
import com.devcommunity.junyharang.common.config.security.dto.TokenDTO;
import com.devcommunity.junyharang.common.config.security.service.CustomUserDetailService;
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

    private final CustomUserDetailService customUserDetailService;

    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserDAO userDAO;

    private final TokenProvider tokenProvider;

    @ApiOperation(value = SwaggerApiInfo.DUPLICATE_ID, notes = "Login 서비스 입니다.")
    @ApiParam(name = "user", value = "Token과 회원 정보를 넘겨주어야 합니다.", readOnly = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login 성공"),
            @ApiResponse(code = 500, message = "Server Internal Error")
    })

    @PostMapping("/signin") public ResponseEntity<TokenDTO> signIn(@RequestBody SignInRequestDTO signInRequestDTO) {

        // TODO - 비즈니스 로직 Service로 변경 필요

        log.info("AuthenticateController의 signin(@RequestBody String user)이 호출 되었습니다!");
        log.info("SignInRequestDTO의 UserName, password를 매개변수로 받아 UsernamePasswordAuthenticationToken을 만들겠습니다!");

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(signInRequestDTO.getUsername(), signInRequestDTO.getPassword());

        log.info("authenticationToken을 이용하여 Authentication 객체를 만들 것인데, authenticate Method를 실행 할 때, loadUserByUsername Method가 호출 됩니다!");

        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authToken);

        log.info("Authentication객체를 SecurityContext에 저장하겠습니다!");

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        log.info("Authentication 객체로 createToken Method 이용 JWT 생성 하겠습니다!");

        String jwt = tokenProvider.createToken(authenticate);

        log.info("Client에게 jwt를 넘겨 주기 위해 Response Header에 넣어주겠습니다!");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        log.info("TokenDTO를 이용하여 Response Body에도 넣어 응답 해 주겠습니다!");

        return new ResponseEntity<>(new TokenDTO(jwt), httpHeaders, HttpStatus.OK);

    } // signin(@RequestBody String user) 끝

} // class 끝
