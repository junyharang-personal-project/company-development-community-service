package com.devcommunity.junyharang.service.support;

import com.devcommunity.junyharang.model.dto.support.DevInquryReplyDeleteDTO;
import com.devcommunity.junyharang.model.vo.support.DevInquryReplyVO;
import com.devcommunity.junyharang.model.vo.support.DevInquryVO;

/**
 * 개발자 Q&A 답변 관련 비즈니스 Logic Interface
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.20 최초 작성
 *    주니하랑, 1.0.1, 2022.03.22 답변 삭제 기능 구현
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.1, 2022.03.22 답변 삭제 기능 구현
 * @See ""
 * @see <a href=""></a>
 */

public interface DevInquryReplyService {

    /**
     * 답글 등록
     * @param devInquryReplyVO 답변 등록 시 내용을 담은 Value Object
     */

    Object devInquryReplyRegist(DevInquryReplyVO devInquryReplyVO);


    /**
     * 답글 삭제
     * @param inqrySn 답글 삭제 시 삭제할 게시글 번호를 담은 변수
     */

    Object devInquryReplyDelete(int inqrySn);
} // class 끝
