package com.devcommunity.junyharang.service.support;

import com.devcommunity.junyharang.mapper.support.DevInquryMapper;
import com.devcommunity.junyharang.model.vo.support.DevInquryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Transactional(rollbackFor = Exception.class) @RequiredArgsConstructor
@Service public class DevInquryServiceImpl implements DevInquryService{

    private final DevInquryMapper devInquryMapper;

//    @Override
//    public void devInquryInsert(DevInquryVO devInquryVO) {
//
//        log.info("DevInquryService를 구현한 DevInquryServiceImpl의 devInquryInsert(DevInquryVO devInquryVO)가 호출 되었습니다!");
//        log.info("devInquryMapper.devInquryInsert(devInquryVO)를 호출 하겠습니다!");
//
//        devInquryMapper.devInquryInsert(devInquryVO);
//    } // devInquryInsert(DevInquryVO devInquryVO) 끝

    /**
     * 목록 조회
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

    // TODO - 목록 조회 시 VO에 Data를 받으므로, 불필요한 Data가 전달 될 수 있으며, 검색이 함께 이뤄지는 Logic으로 분리 및 Refactoring 예정

    @Override
    public List<HashMap<String, Object>> devInquryList(DevInquryVO devInquryVO) {

        log.info("DevInquryService를 구현한 DevInquryServiceImpl의 devInquryList(DevInquryVO devInquryVO)가 호출 되었습니다!");
        log.info("devInquryMapper.devInquryList(devInquryVO)를 호출 하겠습니다!");

        return devInquryMapper.devInquryList(devInquryVO);
    } // devInquryList(DevInquryVO devInquryVO) 끝


//    @Override
//    public void devInquryUpdate(DevInquryVO devInquryVO) {
//
//        log.info("DevInquryService를 구현한 DevInquryServiceImpl의 devInquryUpdate(DevInquryVO devInquryVO)가 호출 되었습니다!");
//        log.info("devInquryMapper.devInquryInsert(devInquryVO)를 호출 하겠습니다!");
//
//        devInquryMapper.devInquryUpdate(devInquryVO);
//    } // devInquryUpdate(DevInquryVO devInquryVO) 끝

    /**
     * 조회수 Count
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return 조회수
     */

    @Override
    public int devInquryReadhitCount(DevInquryVO devInquryVO) {

        log.info("DevInquryService를 구현한 DevInquryServiceImpl의 devInquryList(DevInquryVO devInquryVO)가 호출 되었습니다!");
        log.info("devInquryMapper.devInquryList(devInquryVO)를 호출 하겠습니다!");

        return devInquryMapper.devInquryReadhitCount(devInquryVO);
    }
} // class 끝
