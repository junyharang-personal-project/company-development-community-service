package com.devcommunity.junyharang.service.support;

import com.devcommunity.junyharang.model.vo.support.DevInquryReplyVO;

/**
 * 개발자 Q&A 답변 관련 비즈니스 Logic Interface
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.20 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.20 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

public interface DevInquryReplyService {

    /**
     * 답글 등록
     * @param devInquryReplyVO 답변 등록 시 내용을 담은 Value Object
     */

    Object devInquryReplyRegist(DevInquryReplyVO devInquryReplyVO);

} // class 끝
