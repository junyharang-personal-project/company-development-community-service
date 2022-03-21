package com.devcommunity.junyharang.service.support;

import com.devcommunity.junyharang.mapper.support.DevInquryReplyMapper;
import com.devcommunity.junyharang.model.dto.support.DevInquryReplyDeleteDTO;
import com.devcommunity.junyharang.model.vo.support.DevInquryReplyVO;
import com.devcommunity.junyharang.model.vo.support.DevInquryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Q&A 게시판 답변 Service 구현체
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.22 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.22 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

@Slf4j @Transactional(rollbackFor = Exception.class) @RequiredArgsConstructor
@Service public class DevInquryReplyServiceImpl implements DevInquryReplyService{


    private final DevInquryReplyMapper devInquryReplyMapper;

    /**
     * 게시글 등록
     * @param devInquryReplyVO 답글 등록 시 내용을 담은 Value Object
     */

    @Override
    public Object devInquryReplyRegist(DevInquryReplyVO devInquryReplyVO) {

        log.info("DevInquryReplyService를 구현한 DevInquryReplyServiceImpl의 devInquryReplyRegist(DevInquryReplyVO devInquryReplyVO)가 호출 되었습니다!");

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

//            devInquryReplyVO.setAnswerAt("Y");

            // TODO - 회원가입 및 로그인 로직 구현 뒤 아래 하드코딩 수정 필요
            log.info("답글 작성자에 대한 내용을 VO에 입력하겠습니다!");
            devInquryReplyVO.setAnswerUserSn(1);

            log.info("devInquryReplyMapper의 devInquryReplyRegist(devInquryReplyVO)를 호출 하겠습니다!");
            devInquryReplyMapper.devInquryReplyRegist(devInquryReplyVO);

            result.put("code", 201);
            result.put("message", "답글 등록 성공!");
            result.put("resultSn", devInquryReplyVO.getInqrySn());

            return result;

        } catch (Exception e) {

            log.warn("답글 등록 중 문제가 발생하여 Catch절이 동작하였습니다!");

            e.printStackTrace();

            log.warn(e.getMessage());

            result.put("code", 500);

            return result;
        } // try-catch 끝
    } // devInquryReplyRegist(DevInquryReplyVO devInquryReplyVO) 끝


    /**
     * 답글 삭제
     * @param inqrySn 답글 삭제 시 삭제할 게시글 번호를 담은 변수
     */

    @Override
    public Object devInquryReplyDelete(int inqrySn) {
        Map<String, Object> result = new HashMap<>();

        log.info("DevInquryReplyService를 구현한 DevInquryReplyServiceImpl의 devInquryReplyDelete(int inqrySn)가 호출 되었습니다!");

        try {
            log.info("devInquryReplyMapper.devInquryReplyDelete(inqrySn)를 호출하여 SQL문을 DB에 전달 하겠습니다!");

            devInquryReplyMapper.devInquryReplyDelete(inqrySn);

            result.put("code", 200);
            result.put("statusText", "OK / 삭제 성공!");
            result.put("InqrySn", inqrySn);

            return result;
        } catch (Exception e) {

            log.info("Q&A 게시글 답변을 삭제 하다가 문제가 발생하여 Catch 절로 넘어 왔습니다!");

            e.printStackTrace();

            log.error(e.getMessage());

            result.put("code", 500);
            result.put("statusText", "Server Internal Error");

            return result;
        } // try - catch 끝
    } // devInquryReplyDelete(DevInquryReplyDeleteDTO devInquryReplyDeleteDTO) 끝
} // class 끝
