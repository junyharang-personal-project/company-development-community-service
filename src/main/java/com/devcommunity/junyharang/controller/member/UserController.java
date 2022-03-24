package com.devcommunity.junyharang.controller.member;

import com.devcommunity.junyharang.common.constant.DefaultResponse;
import com.devcommunity.junyharang.common.constant.ServiceURIMng;
import com.devcommunity.junyharang.common.constant.SwaggerApiInfo;
import com.devcommunity.junyharang.model.vo.member.CustomUserDetails;
import com.devcommunity.junyharang.service.user.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 회원 관련 JPA
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

@Api(tags = {"회원 관리 관련 API"}) @Slf4j @RequiredArgsConstructor
@RequestMapping(ServiceURIMng.USER)
@RestController public class UserController {

    private final UserService userService;

    @ApiOperation(value = SwaggerApiInfo.SIGN_UP, notes = "회원 가입 서비스 입니다.")
    @ApiParam(name = "MemberSignUpRequestDTO", value = "이용을 원하는 이용자가 회원 가입을 합니다. \n 필수 : 자기 소개, 프로필 사진을 제외한 모든 항목 \n 비밀번호는 암호화 처리", readOnly = true)
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Email 중복"),
            @ApiResponse(code=201, message = "가입 성공"),
            @ApiResponse(code = 500, message = "Server Internal Error")
    })

    @PostMapping("/signup") public ResponseEntity<DefaultResponse> signUp (@Valid @RequestBody CustomUserDetails customUserDetails) {

        log.info("UserController의 signUp (@Valid @RequestBody UserSignUpRequestDTO userSignUpRequestDTO)이 호출 되었습니다!");
        log.info("userService.signUp(userSignUpRequestDTO)를 호출하여 비즈니스 로직을 처리하겠습니다!");

        return new ResponseEntity<>(userService.signUp(customUserDetails), HttpStatus.OK);

    } // signUp (@Valid @RequestBody UserSignUpRequestDTO userSignUpRequestDTO) 끝

} // class 끝
