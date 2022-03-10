package com.devcommunity.junyharang.mapper.support;

import com.devcommunity.junyharang.model.vo.support.DevInquryVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper @Repository public interface DevInquryMapper {

    /**
     * 게시글 등록
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

//    public void devInquryInsert(DevInquryVO devInquryVO);

    /**
     * 목록 조회
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

    // TODO - 목록 조회 시 VO에 Data를 받으므로, 불필요한 Data가 전달 될 수 있으며, 검색이 함께 이뤄지는 Logic으로 분리 및 Refactoring 예정

    List<HashMap<String, Object>> devInquryList(DevInquryVO devInquryVO);

    /**
     * 게시글 수정
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

//    public void devInquryUpdate(DevInquryVO devInquryVO);


    /**
     * 조회수 Count
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return 조회수
     */

    int devInquryReadhitCount(DevInquryVO devInquryVO);
} // interface 끝
