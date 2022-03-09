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

    public void devInquryInsert(DevInquryVO devInquryVO);

    /**
     * 목록 조회
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

    List<HashMap<String, Object>> devInquryList(DevInquryVO devInquryVO);

    /**
     * 게시글 수정
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

    public void devInquryUpdate(DevInquryVO devInquryVO);


} // interface 끝
