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
        log.info("customUserDetailService.loadUserByUsername(user)를 호출하겠습니다!");

        log.info("JSON 형태로 들어온 이용자의 ID와 비밀번호 정보를 Parsing 하겠습니다.");

//        log.info("JSON 형태로 들어온 이용자의 ID를 문자열 객체 Type \"username\"에 저장 하겠습니다!");
//        String username = signInRequestDTO.getUsername();
//
//        log.info("username : " + username);
//
        log.info("JSON 형태로 들어온 이용자의 비밀번호를 문자열 객체 Type \"password\"에 저장 하겠습니다!");
        String password = signInRequestDTO.getPassword();
//
//        log.info("password : " + password);

//        JSONParser jsonParser = new JSONParser();
//        JSONObject parse = null;

        try {
//            parse = (JSONObject) jsonParser.parse(user);

            log.info("JSON 형태로 들어온 이용자의 ID를 문자열 객체 Type \"username\"에 저장 하겠습니다!");
       //     String username = (String) parse.get("username");

            log.info("JSON 형태로 들어온 이용자의 비밀번호를 문자열 객체 Type \"password\"에 저장 하겠습니다!");
//            String password = (String) parse.get("password");

            CustomUserDetails userInfo = customUserDetailService.loadUserByUsername(signInRequestDTO.getUsername());

            log.info("HttpHeader에 JSON 형태로 응답값 전달을 위해 반환 작업을 하겠습니다!");
            String convertToJSON = objectMapper.writeValueAsString(userInfo);

            log.info("JSON으로 변환 된 값을 확인 하겠습니다! JSON 값 : " + convertToJSON);
            log.info("로그인 이용자가 입력한 ID가 DB에 존재하고, 비밀번호가 일치할 경우에 대한 Logic 처리를 시작합니다!");

            if (userInfo != null) {
                log.info("DB에서 로그인 요청 이용자가 입력한 ID에 해당하는 회원 정보를 찾았습니다!");

                String findDBPWD = userInfo.getPassword();

                log.info("Hashing 암호화로 암호화된 비밀번호와 이용자가 입력한 비밀번호가 일치하는지 확인 하겠습니다!");
                if (passwordEncoder.matches(password, findDBPWD)) {

                    log.info("입력한 비밀번호와 DB에 암호화된 비밀번호가 서로 일치합니다!");
                    log.info("회원 ID, Password를 이용하여 authenticationToken 객체를 만들겠습니다!");

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(signInRequestDTO.getUsername(), password);

                    // TODO - ??
                    Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authToken);

                    log.info("SecurityContext에 인증 정보를 저장 하겠습니다!");
                    SecurityContextHolder.getContext().setAuthentication(authenticate);

                    log.info("인증 정보를 기반으로 JWT를 생성하겠습니다!");
                    String jwt = tokenProvider.createToken(authenticate);

                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "bearer " + jwt);
                    httpHeaders.add("user", convertToJSON);

                    return new ResponseEntity<>(new TokenDTO(jwt), httpHeaders, HttpStatus.OK);
                } // if (passwordEncoder.matches(password, findDBPWD)) 끝
            } // if (userInfo != null) 끝

//        } catch (ParseException e) {
//            e.printStackTrace();
//            log.error("JSON 형태의 로그인 이용자 정보를 Parsing 하던 도 중 문제가 발생 하였습니다!");
//            log.error(e.getMessage());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;

    } // signin(@RequestBody String user) 끝

} // class 끝
