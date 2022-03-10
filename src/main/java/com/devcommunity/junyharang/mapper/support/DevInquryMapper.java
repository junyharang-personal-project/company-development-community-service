package com.devcommunity.junyharang.mapper.support;

import com.devcommunity.junyharang.model.vo.support.DevInquryVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * 개발자 Q&A 관련 DB SQL 처리를 위한 XML Mapper Interface
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.10 최초 작성
 *    주니하랑, 1.0.1, 2022.03.10 CRUD 및 조회수 Count 구현 완료
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.1, 2022.03.10 CRUD 및 조회수 Count 구현 완료
 * @See ""
 * @see <a href=""></a>
 */

@Mapper @Repository public interface DevInquryMapper {

    /**
     * 게시글 등록
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

    void devInquryInsert(DevInquryVO devInquryVO);

    /**
     * 목록 조회
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return List<HashMap<String, Object>> - 여러 목록으로 조회된 Data를 담은 Collection
     */

    // TODO - 목록 조회 시 VO에 Data를 받으므로, 불필요한 Data가 전달 될 수 있으며, 검색이 함께 이뤄지는 Logic으로 분리 및 Refactoring 예정

    List<HashMap<String, Object>> devInquryList(DevInquryVO devInquryVO);

    /**
     * 상세 조회
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return DevInquryVO - 서버 처리 여부에 해당하는 Status Code 및 Data 반환을 위한 객체
     */

    DevInquryVO devInquryDetail(DevInquryVO devInquryVO);

    /**
     * 게시글 수정
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

    void devInquryUpdate(DevInquryVO devInquryVO);

    /**
     * 게시글 삭제
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

    void devInquryDelete(DevInquryVO devInquryVO);

    /**
     * 조회수 Count
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return 조회수
     */

    int devInquryReadhitCount(DevInquryVO devInquryVO);



} // interface 끝
