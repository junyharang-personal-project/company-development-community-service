package com.devcommunity.junyharang.service.support;

import com.devcommunity.junyharang.mapper.support.DevInquryReplyMapper;
import com.devcommunity.junyharang.model.vo.support.DevInquryReplyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j @Transactional(rollbackFor = Exception.class) @RequiredArgsConstructor
public class DevInquryReplyServiceImpl implements DevInquryReplyService{


    private final DevInquryReplyMapper devInquryReplyMapper;

    /**
     * 게시글 등록
     * @param devInquryReplyVO 답글 등록 시 내용을 담은 Value Object
     */

    @Override
    public Object devInquryReplyRegist(DevInquryReplyVO devInquryReplyVO) {
        Map<String, Object> result = new HashMap<>();

        try {
            log.info("답변 내용이 NULL이 아닌지 확인 하겠습니다!");

            if (devInquryReplyVO.getAnswerCn() == null || Objects.equals(devInquryReplyVO.getAnswerDt(), "")) {

                log.info("답변 내용이 NULL 입니다!");

                result.put("code", 400);
                result.put("message", "Bad Request / 답변 내용이 NULL 입니다.");

                log.info("400 Code와 함께 \"Bad Request / 답변 내용이 NULL 입니다.\" 반환하겠습니다!");

                return result;
            } // if (devInquryReplyVO.getAnswerCn() == null || Objects.equals(devInquryReplyVO.getAnswerDt(), "")) 끝

            log.info("답변 내용 등록을 위해 최초 답변 여부(answerAt)을 true로 변경하겠습니다!");

            devInquryReplyVO.setAnswerAt(true);

            // TODO - 회원가입 및 로그인 로직 구현 뒤 아래 하드코딩 수정 필요
            log.info("답글 작성자에 대한 내용을 VO에 입력하겠습니다!");
            devInquryReplyVO.setAnswerUserSn(1);

            devInquryReplyMapper.devInquryReplyRegist(devInquryReplyVO);

            result.put("code", 201);
            result.put("message", "답글 등록 성공!");
            result.put("result", devInquryReplyVO.isAnswerAt());

            return result;

        } catch (Exception e) {

            log.warn("답글 등록 중 문제가 발생하여 Catch절이 동작하였습니다!");

            result.put("code", 500);
            result.put("message", "Server Internal Error");

            return result;
        } // try-catch 끝
    } // devInquryReplyRegist(DevInquryReplyVO devInquryReplyVO) 끝

} // class 끝
