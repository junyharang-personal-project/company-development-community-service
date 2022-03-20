package com.devcommunity.junyharang.mapper.support;

import com.devcommunity.junyharang.model.vo.support.DevInquryReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 개발자 Q&A 답글 관련 DB SQL 처리를 위한 XML Mapper Interface
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

@Mapper @Repository public interface DevInquryReplyMapper {

    /**
     * 답글 등록
     * @param devInquryReplyVO 답글 등록 시 내용을 담은 Value Object
     */

    void devInquryReplyRegist(DevInquryReplyVO devInquryReplyVO);

} // Interface 끝
