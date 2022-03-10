package com.devcommunity.junyharang.service.support;

import com.devcommunity.junyharang.mapper.support.DevInquryMapper;
import com.devcommunity.junyharang.model.vo.support.DevInquryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * 개발자 Q&A 관련 비즈니스 Logic 구현체
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

@Slf4j
@Transactional(rollbackFor = Exception.class) @RequiredArgsConstructor
@Service public class DevInquryServiceImpl implements DevInquryService{

    private final DevInquryMapper devInquryMapper;

    /**
     * 게시글 등록
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

    @Override
    public void devInquryInsert(DevInquryVO devInquryVO) {

        log.info("DevInquryService를 구현한 DevInquryServiceImpl의 devInquryInsert(DevInquryVO devInquryVO)가 호출 되었습니다!");
        log.info("devInquryMapper.devInquryInsert(devInquryVO)를 호출 하겠습니다!");

        devInquryMapper.devInquryInsert(devInquryVO);

    } // devInquryInsert(DevInquryVO devInquryVO) 끝

    /**
     * 목록 조회
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return List<HashMap<String, Object>> - 여러 목록으로 조회된 Data를 담은 Collection
     */

    // TODO - 목록 조회 시 VO에 Data를 받으므로, 불필요한 Data가 전달 될 수 있으며, 검색이 함께 이뤄지는 Logic으로 분리 및 Refactoring 예정

    @Override
    public List<HashMap<String, Object>> devInquryList(DevInquryVO devInquryVO) {

        log.info("DevInquryService를 구현한 DevInquryServiceImpl의 devInquryList(DevInquryVO devInquryVO)가 호출 되었습니다!");
        log.info("devInquryMapper.devInquryList(devInquryVO)를 호출 하겠습니다!");

        return devInquryMapper.devInquryList(devInquryVO);
    } // devInquryList(DevInquryVO devInquryVO) 끝

    /**
     * 상세 조회
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return DevInquryVO - 서버 처리 여부에 해당하는 Status Code 및 Data 반환을 위한 객체
     */

    // TODO - 상세 조회 시 VO에 Data를 받으므로, 불필요한 Data가 전달 될 수 있으며, 게시글의 일련번호만 받게 Refactoring 예정

    @Override
    public DevInquryVO devInquryDetail(DevInquryVO devInquryVO) {

        log.info("DevInquryService를 구현한 DevInquryServiceImpl의 devInquryDetail(DevInquryVO devInquryVO)가 호출 되었습니다!");
        log.info("devInquryMapper.devInquryDetail(devInquryVO)를 호출 하겠습니다!");

        return devInquryMapper.devInquryDetail(devInquryVO);
    } // devInquryDetail(DevInquryVO devInquryVO) 끝


    /**
     * 게시글 수정
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

    @Override
    public void devInquryUpdate(DevInquryVO devInquryVO) {

        log.info("DevInquryService를 구현한 DevInquryServiceImpl의 devInquryUpdate(DevInquryVO devInquryVO)가 호출 되었습니다!");
        log.info("devInquryMapper.devInquryUpdate(devInquryVO)를 호출 하겠습니다!");

        devInquryMapper.devInquryUpdate(devInquryVO);

    } // devInquryUpdate(DevInquryVO devInquryVO) 끝


    /**
     * 게시글 삭제
     * @param devInquryVO 게시글 삭제 시 필요한 내용을 담은 Value Object
     */

    @Override
    public void devInquryDelete(DevInquryVO devInquryVO) {

        log.info("DevInquryService를 구현한 DevInquryServiceImpl의 devInquryDelete(DevInquryVO devInquryVO)가 호출 되었습니다!");
        log.info("devInquryMapper.devInquryDelete(devInquryVO)를 호출 하겠습니다!");

        devInquryMapper.devInquryDelete(devInquryVO);

    } // devInquryDelete(DevInquryVO devInquryVO) 끝

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
