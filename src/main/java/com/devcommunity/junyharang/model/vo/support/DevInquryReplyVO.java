package com.devcommunity.junyharang.model.vo.support;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Q&A 게시판 답글 Value Object
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

@Setter @Getter @ToString
public class DevInquryReplyVO {

    private int inqrySn;
    private String answerAt;
    private String answerCn;
    private int answerUserSn;
    private String answerDt;
} // class 끝
