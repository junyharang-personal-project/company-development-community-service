package com.devcommunity.junyharang.controller.member;

import com.devcommunity.junyharang.common.constant.DefaultResponse;
import com.devcommunity.junyharang.common.constant.ServiceURIMng;
import com.devcommunity.junyharang.common.constant.SwaggerApiInfo;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserEmailDTO;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserIDDTO;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserNickNameDTO;
import com.devcommunity.junyharang.model.dto.user.DuplicateByUserPhoneNumberDTO;
import com.devcommunity.junyharang.model.vo.member.CustomUserDetails;
import com.devcommunity.junyharang.service.user.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 회원 관련 Controller
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.24 최초 작성
 *    주니하랑, 1.0.1, 2022.03.27 Validation 기능 구현을 위한 중복 확인 Method 별 매개 변수 DTO 재 정의
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.1, 2022.03.27 Validation 기능 구현을 위한 중복 확인 Method 별 매개 변수 DTO 재 정의
 * @See ""
 * @see <a href=""></a>
 */

@Api(tags = {"회원 관리 관련 API"}) @Slf4j @RequiredArgsConstructor
@RequestMapping(ServiceURIMng.USER)
@RestController public class UserController {

    private final UserService userService;

    @ApiOperation(value = SwaggerApiInfo.DUPLICATE_ID, notes = "회원 가입 전 ID 중복 확인 서비스 입니다.")
    @ApiParam(name = "DuplicateByIdInfoDTO", value = "이용을 원하는 이용자가 회원 가입을 위해 ID 중복 확인 합니다.", readOnly = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "사용 가능"),
            @ApiResponse(code = 409, message = "입력값 중복"),
            @ApiResponse(code = 500, message = "Server Internal Error")
    })

    @ResponseBody
    @PostMapping("/duplicate/userid") public DefaultResponse duplicateUserID(@Valid @RequestBody DuplicateByUserIDDTO duplicateByUserIDDTO) {

        log.info("UserController의 duplicateUserID(@RequestBody DuplicateByIdInfoDTO duplicateByIdInfoDTO)가 호출 되었습니다! Client로 부터 전달된 값 확인 : " + duplicateByUserIDDTO.toString());
        log.info("userService.duplicateUserID(duplicateByIdInfoDTO)를 호출하여 비즈니스 로직을 처리하겠습니다!");

        return userService.duplicateUserID(duplicateByUserIDDTO);

    } // duplicateId(@RequestBody String username) 끝

    @ApiOperation(value = SwaggerApiInfo.DUPLICATE_NICKNAME, notes = "회원 가입 전 별명 중복 확인 서비스 입니다.")
    @ApiParam(name = "DuplicateByIdInfoDTO", value = "이용을 원하는 이용자가 회원 가입을 위해 별명 중복 확인 합니다.", readOnly = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "사용 가능"),
            @ApiResponse(code = 409, message = "입력값 중복"),
            @ApiResponse(code = 500, message = "Server Internal Error")
    })

    @ResponseBody
    @PostMapping("/duplicate/nickname") public DefaultResponse duplicateUserNickName(@Valid @RequestBody DuplicateByUserNickNameDTO duplicateByUserNickNameDTO) {

        log.info("UserController의 duplicateUserNickName(@RequestBody DuplicateByIdInfoDTO duplicateByIdInfoDTO)가 호출 되었습니다! Client로 부터 전달된 값 확인 : " + duplicateByUserNickNameDTO.toString());
        log.info("userService.duplicateUserNickName(duplicateByIdInfoDTO)를 호출하여 비즈니스 로직을 처리하겠습니다!");

        return userService.duplicateUserNickName(duplicateByUserNickNameDTO);

    } // duplicateId(@RequestBody String username) 끝

    @ApiOperation(value = SwaggerApiInfo.DUPLICATE_EMAIL, notes = "회원 가입 전 E-mail 중복 확인 서비스 입니다.")
    @ApiParam(name = "DuplicateByIdInfoDTO", value = "이용을 원하는 이용자가 회원 가입을 위해 mail 중복 확인 합니다.", readOnly = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "사용 가능"),
            @ApiResponse(code = 409, message = "입력값 중복"),
            @ApiResponse(code = 500, message = "Server Internal Error")
    })

    @ResponseBody
    @PostMapping("/duplicate/email") public DefaultResponse duplicateUserEmail(@Valid @RequestBody DuplicateByUserEmailDTO duplicateByUserEmailDTO) {

        log.info("UserController의 duplicateUserEmail(@RequestBody DuplicateByIdInfoDTO duplicateByIdInfoDTO)가 호출 되었습니다! Client로 부터 전달된 값 확인 : " + duplicateByUserEmailDTO.toString());
        log.info("userService.duplicateUserEmail(duplicateByIdInfoDTO)를 호출하여 비즈니스 로직을 처리하겠습니다!");

        return userService.duplicateUserEmail(duplicateByUserEmailDTO);

    } // duplicateId(@RequestBody String username) 끝

    @ApiOperation(value = SwaggerApiInfo.DUPLICATE_PHONE_NUMBER, notes = "회원 가입 전 핸드폰 번호 중복 확인 서비스 입니다.")
    @ApiParam(name = "DuplicateByIdInfoDTO", value = "이용을 원하는 이용자가 회원 가입을 위해 핸드폰 번호 중복 확인 합니다.", readOnly = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "사용 가능"),
            @ApiResponse(code = 409, message = "입력값 중복"),
            @ApiResponse(code = 500, message = "Server Internal Error")
    })

    @ResponseBody
    @PostMapping("/duplicate/phone-number") public DefaultResponse duplicateUserPhoneNumber(@Valid @RequestBody DuplicateByUserPhoneNumberDTO duplicateByUserPhoneNumberDTO) {

        log.info("UserController의 duplicateUserPhoneNumber(@RequestBody DuplicateByIdInfoDTO duplicateByIdInfoDTO)가 호출 되었습니다! Client로 부터 전달된 값 확인 : " + duplicateByUserPhoneNumberDTO.toString());
        log.info("userService.duplicateUserPhoneNumber(duplicateByIdInfoDTO)를 호출하여 비즈니스 로직을 처리하겠습니다!");

        return userService.duplicateUserPhoneNumber(duplicateByUserPhoneNumberDTO);

    } // duplicateId(@RequestBody String username) 끝

    @ApiOperation(value = SwaggerApiInfo.SIGN_UP, notes = "회원 가입 서비스 입니다.")
    @ApiParam(name = "customUserDetails", value = "이용을 원하는 이용자가 회원 가입을 위해 작성한 내용을 담은 객체 입니다. \n 필수 : 자기 소개, 프로필 사진을 제외한 모든 항목 \n 비밀번호는 암호화 처리", readOnly = true)
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
