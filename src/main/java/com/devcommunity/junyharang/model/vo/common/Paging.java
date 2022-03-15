package com.devcommunity.junyharang.model.vo.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.PrimitiveIterator;

/**
 * 게시판 공통 Value 처리를 위한 Value Object
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

@Slf4j @Data
public class Paging extends Criteria{

    private int totalCount;                     // 게시판 전체 Data 개수
    private int displayPageNum = 10;            // 게시판 화면에서 목록 조회 시 보여질 페이지 번호의 개수

    private int startPage;                      //  페이징 처리의 시작 번호
    private int endPage;                        // 페이징 처리의 끝 번호
    private int pageStart;
    private boolean prev;                       // 페이징 이전 버튼 활성화 여부
    private boolean next;                       // 페이징 다음 버튼 활성화 여부

    public int getTotalCount() {
      log.info("Paging의 getTotalCount가 호출 되었습니다!");
      log.info("게시판 전체 Data 개수를 반환 하겠습니다! : " + totalCount);
      return totalCount;
    } // getTotalCount() 끝

    public void setTotalCount(int totalCount) {
        log.info("Paging의 setTotalCount가 호출 되었습니다!");
        this.totalCount = totalCount;
        this.pageStart = super.getPageStart();

        pagingData();
    } // setTotalCount(int totalCount) 끝

    private void pagingData() {

        log.info("Paging의 pagingData()가 호출 되었습니다!");
        log.info("페이징 처리의 끝 번호를 계산 하겠습니다!");

        endPage = (int) (Math.ceil(super.getPage() / (double) displayPageNum) * displayPageNum);

        log.info("페이징 처리 끝 번호 : " + endPage);
        log.info("페이징 처리의 시작 번호를 계산 하겠습니다!");

        startPage = (endPage - displayPageNum) + 1;

        log.info("페이징 처리 시작 번호 : " + startPage);

        int tempEndPage = (int) (Math.ceil(totalCount / (double) super.getPerPageNum()));

        log.info("임시 끝 번호 : " + tempEndPage);

        if (endPage > tempEndPage) {

            endPage = tempEndPage;

        } // if (endPage > tempEndPage) 끝

        // 이전 Page 이동 Button 생성 여부 : 시작 Page 번호가 1과 같으면 만들지 않기 위해 false, 아니면 만들기 위해 true 반환
        prev = startPage == 1 ? false : true;

        log.info("페이지 이전 값(prev) : " + prev);

        // 다음 Page 이동 Button 생성 여부 : 끝 페이지 번호 * 한 Page 당 보여 줄 게시글의 개수가 총 게시글의 수보다 크거나 같으면 만들지 않기 위해 false, 아니면 만들기 위해 true 반환
        next = endPage * super.getPerPageNum() >= totalCount ? false : true;

        log.info("페이지 다음 값(next)" + next);
    } // pagingData()

} // class 끝
