package com.devcommunity.junyharang.controller.support;

import com.devcommunity.junyharang.common.constant.ServiceURIMng;
import com.devcommunity.junyharang.common.constant.SwaggerApiInfo;
import com.devcommunity.junyharang.common.util.StringUtil;
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
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.09 최초 작성
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


    /**
     * 게시글 등록 / 수정 서비스
     * @param devInquryVO - 회원 가입을 위한 이용자 입력값을 담은 DTO
     * @return request - 서버 처리 여부에 해당하는 Status Code 반환을 위한 객체
     * @see ""
     */

    @ApiOperation(value = SwaggerApiInfo.GET_POSTS_LIST, notes = "Q&A 목록 조회 서비스 입니다.")
    @ApiParam(name = "devInquryVO", value = "조회 시 입력 되어야 할 내용 객체 입니다.", readOnly = true)
    @ApiResponses(value = { @ApiResponse(code=200, message = "1.등록 성공 \n 2. 등록 실패 \n 3.Token Error")})

    /**
     * 목록 조회 서비스
     * @param devInquryVO - 회원 가입을 위한 이용자 입력값을 담은 DTO
     * @return Object - 서버 처리 여부에 해당하는 Status Code 반환을 위한 객체
     * @see ""
     */

    @ResponseBody
    @GetMapping("/devInquryList") public Object devInquryList(@RequestBody DevInquryVO devInquryVO) throws Exception {

        log.info("DevInquryController의 devInquryList(@RequestBody DevInquryVO devInquryVO)가 호출 되었습니다!");

        Map<String, Object> result = new HashMap<>();

        log.info("개발자 문의 게시글 목록을 가져오기 위해 devInquryService.devInquryList(devInquryVO)를 호출 하겠습니다!");
        List<HashMap<String, Object>> devInquryList = devInquryService.devInquryList(devInquryVO);

        log.info("개발자 문의 게시글 목록을 Count하기 위해 devInquryService.devInquryListCnt(devInquryVO)를 호출 하겠습니다!");
        int devInquryListCnt = devInquryService.devInquryListCnt(devInquryVO);

        log.info("각 Service에서 조회된 결과값을 result Map에 담아 반환 하겠습니다!");
        result.put("devInquryList", devInquryList);
        result.put("devInquryListCnt", devInquryListCnt);

        return result;

    } // devInquryList(@RequestBody DevInquryVO devInquryVO) 끝

} // class 끝
