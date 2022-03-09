package com.devcommunity.junyharang.model.vo.support;

import com.devcommunity.junyharang.model.vo.common.CommonVO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Q&A 게시판 Value Object
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

@Getter @Builder @ToString
public class DevInquryVO extends CommonVO {

    private int InqrySn;
    private int InqryUserSn;
    private int fileSn;
    private String inqryCn;
    private String secretAt;
    private String answerAt;
    private String answerCn;
    private int answerUserSn;
    private String answerDt;
    private String creatDt;
    private int crtrSn;
    private String updtDt;
    private int updusrSn;
    private String inqrySj;
    private String srchWord;
    private String srchType;
    private String userId;
    private int beforeInqrySn;
    private String beforeInqrySj;
    private int nextInqrySn;
    private String nextInqrySj;
    private int inqryIndex;
    private String answerUserId;

} // class 끝
