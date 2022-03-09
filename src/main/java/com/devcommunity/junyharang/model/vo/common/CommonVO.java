package com.devcommunity.junyharang.model.vo.common;

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

public class CommonVO extends Paging{

    private String rowNumber;	// 행번호
    private String regDt;		// 등록일시
    private String regId;		// 등록ID
    private String regUserNm;	// 등록자명
    private String regIp;		// 등록IP
    private String updtDt;		// 수정일시
    private String updtId;		// 수정ID
    private String updtUserNm;	// 수정자명
    private String updtIp;		// 수정IP

    private String creatDt;		// 생성 일시
    private int crtrSn;			// 생성자 일련번호
    //private String updtDt;		// 수정 일시
    private int updusrSn;		// 수정자 일련번호

} // class 끝
