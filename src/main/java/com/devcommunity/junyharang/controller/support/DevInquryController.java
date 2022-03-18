package com.devcommunity.junyharang.controller.support;

import com.devcommunity.junyharang.common.constant.ServiceURIMng;
import com.devcommunity.junyharang.common.constant.SwaggerApiInfo;
import com.devcommunity.junyharang.common.util.CustomStringUtil;
import com.devcommunity.junyharang.model.vo.support.DevInquryVO;
import com.devcommunity.junyharang.service.support.DevInquryService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 개발자 Q&A 관련 API(Router)
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.09 최초 작성
 *    주니하랑, 1.0.1, 2022.03.10 CRUD 및 조회수 Count 구현 완료
 *    주니하랑, 1.0.2, 2022.03.16 FrontEnd (Vue.js) 동작 구현을 위해 임시로 HTTP Method 변경
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.2, 2022.03.16 FrontEnd (Vue.js) 동작 구현을 위해 임시로 HTTP Method 변경
 * @See ""
 * @see <a href=""></a>
 */

@RequiredArgsConstructor @Api(tags = {"Q&A 관련 API"}) @ApiOperation(value = SwaggerApiInfo.POSTING)
@Slf4j @RequestMapping(value = ServiceURIMng.SUPPORT_SERVICE)
@RestController public class DevInquryController {

    private DevInquryService devInquryService;

    @Autowired public DevInquryController(DevInquryService devInquryService) {

        log.info("DevInquryController의 생성자 DevInquryController(DevInquryService devInquryService, FileService fileService)가 호출 되었습니다!");

        this.devInquryService = devInquryService;

    } // DevInquryController(DevInquryService devInquryService, FileService fileService) 끝

    @ApiOperation(value = SwaggerApiInfo.WRITE_UPDATE_POSTS, notes = "Q&A 게시글 등록 / 수정 서비스 입니다.")
    @ApiParam(name = "devInquryVO", value = "등록 / 수정 시 입력 되어야 할 내용 객체 입니다.", readOnly = true)
    @ApiResponses(value = { @ApiResponse(code=200, message = "1.등록 성공 \n 2.등록 실패")})

    /**
     * 게시글 등록 / 수정 서비스
     * @param devInquryVO - 회원 가입을 위한 이용자 입력값을 담은 DTO
     * @return Object - 서버 처리 여부에 해당하는 Status Code 및 Data 반환을 위한 객체
     * @see ""
     */

    // TODO - Controller에 있는 비즈니스 로직 ServiceImpl로 옮겨야 하며, 등록 / 수정 분리 작업 필요

    @ResponseBody @PostMapping("/devInqury")
    public Object devInquryRegist(@RequestBody DevInquryVO devInquryVO, HttpServletRequest request) throws Exception {

        log.info("devInquryRegist(@RequestBody DevInquryVO devInquryVO, HttpServletRequest request)가 호출 되었습니다!");
        log.info("Client에서 넘어온 Data Value를 먼저 확인 하기 위해 배열 변수에 값을 넣겠습니다!");

        Field[] fields = devInquryVO.getClass().getDeclaredFields();

        log.info("반복문을 통해 배열에 들어 간 Data를 하나씩 꺼내 확인 해 보겠습니다!");
        for (Field field : fields) {

            /** 참고 자료
             * @see "https://tyboss.tistory.com/entry/Java-%EC%9E%90%EB%B0%94-%EB%A6%AC%ED%94%8C%EB%A0%89%EC%85%98-reflection-setAccessible"
             */

            log.info("Java 리플렉션 기법 중 setAccessible(true) 통해 Field 객체 자료형 Type field의 접근제어 지시자에 의한 제어를 변경 하겠습니다!");
            field.setAccessible(true);

            System.err.println(field.getName() + " : " + field.get(devInquryVO));

        } // for (Field field : fields) 끝

        Map<String, Object> result = new HashMap<>();

        log.info("devInquryVO 값 : " + devInquryVO.toString());

        try {

            log.info("등록할 글이 비밀글인지 확인 하겠습니다!");
            if (devInquryVO.getSecretAt().equals("false")) {

                log.info("등록할 글이 비밀글이 아닙니다! 공개글로 등록 합니다!");
                devInquryVO.setSecretAt("N");

            } else {

                log.info("등록할 글이 비밀글 입니다! 비밀글로 등록 합니다!");
                devInquryVO.setSecretAt("Y");

            } // if (devInquryVO.getSecretAt().equals("false")) - else 끝

            log.info("등록 / 수정 판별을 위해 등록 요청에 게시글 일련번호가 있는지 확인 하겠습니다!");
            // TODO : 등록 / 수정 Logic 분리 필요
            if (CustomStringUtil.getString(devInquryVO.getInqrySn()) == "" || devInquryVO.getInqrySn() == 0) {

                log.info("등록 요청 게시글 일련번호가 비어있습니다! 게시글 등록을 수행 합니다!");
                // TODO - 회원가입 및 로그인 로직 구현 뒤 아래 하드코딩 수정 필요
                devInquryVO.setInqryUserSn(4);

                log.info("devInquryService.devInquryInsertUpdate(devInquryVO)을 호출 하겠습니다!");
                devInquryService.devInquryInsert(devInquryVO);

                log.info("Map result에 게시글 일련 번호를 넣겠습니다!");
                result.put("resultSn", devInquryVO.getInqrySn());

            } else {

                log.info("등록 요청 게시글 일련번호가 존재 합니다! 게시글 수정을 수행 합니다!");
                log.info("devInquryService.devInquryInsertUpdate(devInquryVO)을 호출 하겠습니다!");
                devInquryService.devInquryUpdate(devInquryVO);

                log.info("Map result에 게시글 일련 번호를 넣겠습니다!");
                result.put("resultSn", devInquryVO.getInqrySn());

            } // if (CustomStringUtil.getString(devInquryVO.getInqrySn() == "" || devInquryVO.getInqrySn() == 0)) - else 끝

            log.info("Logic이 완료 되었으므로, 200 Code를 Map result에 넣겠습니다!");
            result.put("code", 200);
        } catch (Exception e) {

            log.info("게시글 등록 / 수정에 문제가 발생(권한 문제)하여 catch문이 실행 되었습니다!");

            e.printStackTrace();

            log.warn(e.getMessage());

            log.info("Logic이 Error로 401 Code를 Map result에 넣겠습니다!");
            result.put("code", 401);

            return result;

        } // try - catch 끝

        return result;

    } // devInquryRegist(@RequestBody DevInquryVO devInquryVO, HttpServletRequest request) 끝

    @ApiOperation(value = SwaggerApiInfo.GET_POSTS_LIST, notes = "Q&A 목록 조회 서비스 입니다.")
    @ApiParam(name = "devInquryVO", value = "조회 시 입력 되어야 할 내용 객체 입니다.", readOnly = true)
    @ApiResponses(value = { @ApiResponse(code=200, message = "1.조회 성공 \n 2.조회 실패")})

    /**
     * 목록 조회 서비스
     * @param devInquryVO - 회원 가입을 위한 이용자 입력값을 담은 DTO
     * @return Object - 서버 처리 여부에 해당하는 Status Code 및 Data 반환을 위한 객체
     * @see ""
     */

    // TODO - 목록 조회 시 VO에 Data를 받으므로, 불필요한 Data가 전달 될 수 있으며, 검색이 함께 이뤄지는 Logic으로 분리 및 Refactoring 예정

    @ResponseBody
//    @PostMapping("/devInquryList")
    @RequestMapping(value = "/devInquryList", method = {RequestMethod.POST})
    public Object devInquryList(@RequestBody DevInquryVO devInquryVO) throws Exception {

        log.info("DevInquryController의 devInquryList(@RequestBody DevInquryVO devInquryVO)가 호출 되었습니다!");

        Map<String, Object> result = new HashMap<>();

        log.info("개발자 문의 게시글 목록을 가져오기 위해 devInquryService.devInquryList(devInquryVO)를 호출 하겠습니다!");
        List<HashMap<String, Object>> devInquryList = devInquryService.devInquryList(devInquryVO);

        log.info("개발자 문의 게시글 목록을 Count하기 위해 devInquryService.devInquryListCnt(devInquryVO)를 호출 하겠습니다!");
        int devInquryListCnt = devInquryService.devInquryReadhitCount(devInquryVO);

        log.info("각 Service에서 조회된 결과값을 result Map에 담아 반환 하겠습니다!");
        result.put("devInquryList", devInquryList);
        result.put("devInquryListCnt", devInquryListCnt);

        return result;

    } // devInquryList(@RequestBody DevInquryVO devInquryVO) 끝

    @ApiOperation(value = SwaggerApiInfo.GET_POSTS_ONE_THING, notes = "Q&A 상세 조회 서비스 입니다.")
    @ApiParam(name = "devInquryVO", value = "조회 시 입력 되어야 할 내용 객체 입니다.", readOnly = true)
    @ApiResponses(value = { @ApiResponse(code=200, message = "1.조회 성공 \n 2.조회 실패")})

    /**
     * 상세 조회 서비스
     * @param devInquryVO - 회원 가입을 위한 이용자 입력값을 담은 DTO
     * @return Object - 서버 처리 여부에 해당하는 Status Code 및 Data 반환을 위한 객체
     * @see ""
     */

    @ResponseBody // @PostMapping("/devInqury")
    @RequestMapping(value = "/devInquryView", method = {RequestMethod.POST})
    public Object devInquryDetail (@RequestBody DevInquryVO devInquryVO) throws Exception {

        log.info("DevInquryController의 devInquryDetail (@RequestBody DevInquryVO devInquryVO)가 호출 되었습니다!");
        log.info("Client에서 넘어온 Data Value를 먼저 확인 하기 위해 배열 변수에 값을 넣겠습니다!");

        Field[] fields = devInquryVO.getClass().getDeclaredFields();

        log.info("반복문을 통해 배열에 들어 간 Data를 하나씩 꺼내 확인 해 보겠습니다!");
        log.info("Java 리플렉션 기법 중 setAccessible(true) 통해 Field 배열 fields의 접근제어 지시자에 의한 제어를 변경 하겠습니다!");
        for (int i = 0; i < fields.length; i++) {

            /** 참고 자료
             * @see "https://tyboss.tistory.com/entry/Java-%EC%9E%90%EB%B0%94-%EB%A6%AC%ED%94%8C%EB%A0%89%EC%85%98-reflection-setAccessible"
             */
            fields[i].setAccessible(true);

            System.err.println(fields[i].getName() + " : " + fields[i].get(devInquryVO));

        } // for (int i = 0; i < fields.length; i++) 끝

        Map<String, Object> result = new HashMap<String, Object>();

        log.info("devInquryService.devInquryDetail(devInquryVO)를 호출하여 비즈니스 로직 처리를 하겠습니다!");
        DevInquryVO valueObject = devInquryService.devInquryDetail(devInquryVO);

        log.info("devInquryService.devInquryDetail(devInquryVO) 반환값을 result Map에 담겠습니다!");
        result.put("devInquryVO", valueObject);

        log.info("result Map을 반환 하겠습니다!");
        return result;

    } // devInquryDetail (@RequestBody DevInquryVO devInquryVO) 끝


    @ApiOperation(value = SwaggerApiInfo.DELETE_POSTS, notes = "Q&A 게시글 삭제 서비스 입니다.")
    @ApiParam(name = "devInquryVO", value = "삭제 시 입력 되어야 할 내용 객체 입니다.", readOnly = true)
    @ApiResponses(value = { @ApiResponse(code=200, message = "1.삭제 성공 \n 2.삭제 실패")})

    /**
     * 상세 조회 서비스
     * @param devInquryVO - 회원 가입을 위한 이용자 입력값을 담은 DTO
     * @return Object - 서버 처리 여부에 해당하는 Status Code 및 Data 반환을 위한 객체
     * @see ""
     */

    @ResponseBody @DeleteMapping("/devInqury")
    public Object devInquryDelete(@RequestBody DevInquryVO devInquryVO) throws Exception {

        log.info("DevInquryController의 devInquryDelete(@RequestBody DevInquryVO devInquryVO)가 호출 되었습니다!");
        log.info("Client에서 넘어온 Data Value를 먼저 확인 하기 위해 배열 변수에 값을 넣겠습니다!");

        Field[] fields = devInquryVO.getClass().getDeclaredFields();

        for (Field field : fields) {

            /** 참고 자료
             * @see "https://tyboss.tistory.com/entry/Java-%EC%9E%90%EB%B0%94-%EB%A6%AC%ED%94%8C%EB%A0%89%EC%85%98-reflection-setAccessible"
             */

            log.info("Java 리플렉션 기법 중 setAccessible(true) 통해 Field 객체 자료형 Type field의 접근제어 지시자에 의한 제어를 변경 하겠습니다!");
            field.setAccessible(true);

            System.err.println(field.getName() + " : " + field.get(devInquryVO));

        } // for (Field field : fields) 끝

        Map<String, Object> result = new HashMap<>();

        try {

            log.info("Q&A 게시글 삭제가 시작 됩니다!");
            log.info("devInquryService.devInquryDelete(devInquryVO)를 호출 하겠습니다!");

            devInquryService.devInquryDelete(devInquryVO);

            log.info("비즈니스 로직이 정상 처리 되었으므로, Map result에 200 Code를 담겠습니다!");

            result.put("code", 200);

        } catch (Exception e) {

            log.info("게시글 삭제 중 문제가 발생하여 catch문이 실행 되었습니다!");

            e.printStackTrace();

            log.warn(e.getMessage());

            log.info("Logic이 Error로 401 Code를 Map result에 넣겠습니다!");
            result.put("code", 401);

            return result;
        } // try - catch 끝

        return result;

    } // devInquryDelete(@RequestBody DevInquryVO devInquryVO) 끝

} // class 끝
