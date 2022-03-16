package com.devcommunity.junyharang.model.vo.common;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 페이징 처리를 위한 페이징 계산식 Class
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
@Slf4j
public class Criteria {

//    // 특정 Page 조회 Class
//    private int page;                           // 현재 Page 번호
//    private int perPageNum;                     // Page 당 보여줄 게시글 개수
//
//    public Criteria() {
//        log.info("Criteria의 기본 생성자 Criteria()가 호출 되었습니다!");
//        log.info("최초 게시판 진입 시 Paging 관련 기본값을 처리 하겠습니다!");
//
//        this.page = 1;
//        this.perPageNum = 10;
//    } // Criteria() 끝
//
//    public int getPageStart() {
//
//        log.info("Criteria의 getPageStart()가 호출 되었습니다!");
//        log.info("이 곳에서 특정 페이지의 범위를 정할 것이고, 현재 Page의 게시글 시작 번호를 기준으로 합니다. 예시 :)0 ~ 10, 10 ~ 20 ");
//
//        log.info("현재 페이지 위치 값 : " + this.page);
//
//        return (this.page -1) * perPageNum;
//    } // getPageStart() 끝
//
//    public int getPerPageNum() {
//        log.info("Criteria의 getPerPageNum()가 호출 되었습니다!");
//        log.info("Page 당 보여줄 게시글 " + perPageNum + " 의 개수를 반환 하겠습니다!");
//
//        return perPageNum;
//    } // getPerPageNum() 끝
//
//    public void setPerPageNum(int perPageNum) {
//        log.info("Criteria의 setPerPageNum(int perPageNum)가 호출 되었습니다!");
//        log.info("Page 당 보여줄 게시글의 개수를 처리하겠습니다!");
//
//        int count = this.perPageNum;
//
//        log.info("page 당 보여줄 게시글 개수 : " + count);
//
//        if (perPageNum != count) {
//            this.perPageNum = perPageNum;
//        } else {
//            this.perPageNum = count;
//        } // if - else 끝
//    } // setPerPageNum(int perPageNum) 끝
//
//    public int getPage() {
//
//        log.info("Criteria의 getPage()가 호출 되었습니다!");
//        log.info("현재 위치의 페이지 번호 " + page + " 를(을) 반환하겠습니다!");
//
//        return page;
//
//    } // getPage() 끝
//
//    public void setPage(int page) {
//
//        log.info("Criteria의 setPage(int page)가 호출 되었습니다!");
//        log.info("현재 위치의 페이지 번호 " + page + " 을(를) 계산하겠습니다!");
//        log.info("page 번호가 0보다 작다면 페이지 번호를 1로 맞춰 주겠습니다!");
//
//        if (page <= 0) {
//            this.page = 1;
//        } else {
//            log.info("page 번호 " + page + " 가 0보다 작지 않다면 페이지 번호를 현재 페이지 번호로 맞춰 주겠습니다!");
//            this.page = page;
//        } // if (page <= 0) - else 끝
//    } // setPage(int page) 끝

    // 특정 페이지 조회를 위한 클래스
    private int page; // 현재 페이지 번호
    private int perPageNum; // 페이지당 보여줄 게시글의 개수


    public int getPageStart() {
        // 특정 페이지의 범위를 정하는 구간, 현재 페이지의 게시글 시작 번호
        // 0 ~ 10 , 10 ~ 20 이런식으로
        return (this.page -1) * perPageNum;
    }

    public Criteria() {
        // 기본 생성자 : 최초 게시판에 진입시 필요한 기본값
        this.page = 1;
        this.perPageNum = 10;
    }

    // 현재 페이지 번호 page : getter, setter
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;

        } else {
            this.page = page;
        }
    }


    // 페이지당 보여줄 게시글의 개수 perPageNum : getter, setter
    public int getPerPageNum() {
        return perPageNum;
    }

    public void setPerPageNum(int perPageNum) {
        int cnt = this.perPageNum;

        if(perPageNum != cnt) {
            this.perPageNum = perPageNum;
        } else {
            this.perPageNum = cnt;
        }

    }

    @Override
    public String toString() {
        return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
    }


} // class 끝
