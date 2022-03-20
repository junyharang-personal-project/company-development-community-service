package com.devcommunity.junyharang.controller.support;

import com.devcommunity.junyharang.common.constant.ServiceURIMng;
import com.devcommunity.junyharang.common.constant.SwaggerApiInfo;
import com.devcommunity.junyharang.model.vo.support.DevInquryReplyVO;
import com.devcommunity.junyharang.service.support.DevInquryReplyService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 개발자 Q&A 관련 API(Router)
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.20 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.2, 1.0.0, 2022.03.20 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

@RequiredArgsConstructor @Slf4j
@Api(tags = {"Q&A 답글 관련 API"}) @ApiOperation(value = SwaggerApiInfo.REPLY)
@RequestMapping(value = ServiceURIMng.SUPPORT_SERVICE)
@RestController public class DevInquryReplyController {

    private DevInquryReplyService devInquryReplyService;

    @ApiOperation(value = SwaggerApiInfo.WRITE_REPLY, notes = "Q&A 답글 등록 서비스 입니다.")
    @ApiParam(name = "devInquryReplyVO", value = "답변 등록 / 수정 시 입력 되어야 할 내용 객체 입니다.", readOnly = true)
    @ApiResponses(value = { @ApiResponse(code=200, message = "1.등록 성공 \n 2.등록 실패")})

    @ResponseBody @PostMapping("/devInqury/Reply")
    public Object devInquryReplyRegist(@RequestBody DevInquryReplyVO devInquryReplyVO) throws Exception {

        log.info("DevInquryReplyController의 devInquryReplyRegist(@RequestBody DevInquryReplyVO devInquryReplyVO)가 동작 했습니다! 이용자가 답글 달기를 시도 합니다!");

        log.info("요청으로 들어온 답글에 대한 VO 내용 : " + devInquryReplyVO.toString());

        return devInquryReplyService.devInquryReplyRegist(devInquryReplyVO);

    } // devInquryReplyRegist(@RequestBody DevInquryReplyVO devInquryReplyVO) 끝

} // class 끝
