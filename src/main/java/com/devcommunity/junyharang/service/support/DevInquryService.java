package com.devcommunity.junyharang.service.support;

import com.devcommunity.junyharang.model.vo.support.DevInquryVO;

import java.util.HashMap;
import java.util.List;

/**
 * 개발자 Q&A 관련 비즈니스 Logic Interface
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

public interface DevInquryService {

    /**
     * 게시글 등록
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

//    void devInquryInsert(DevInquryVO devInquryVO);

    /**
     * 목록 조회
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

    List<HashMap<String, Object>> devInquryList(DevInquryVO devInquryVO);

    /**
     * 게시글 수정
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

//    void devInquryUpdate(DevInquryVO devInquryVO);

} // interface 끝
