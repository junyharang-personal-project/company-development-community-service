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

    // TODO - 목록 조회 시 VO에 Data를 받으므로, 불필요한 Data가 전달 될 수 있으며, 검색이 함께 이뤄지는 Logic으로 분리 및 Refactoring 예정

    List<HashMap<String, Object>> devInquryList(DevInquryVO devInquryVO);

    /**
     * 조회수 Count
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return 조회수
     */

    int devInquryReadhitCount(DevInquryVO devInquryVO);

    /**
     * 게시글 수정
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

//    void devInquryUpdate(DevInquryVO devInquryVO);

} // interface 끝
