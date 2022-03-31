package com.devcommunity.junyharang.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 응답 코드 관련 enum Class
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.31 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.31 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

@Getter @RequiredArgsConstructor
public enum ResponseCode {

    SUCCESS(200, "성공", "Success"),
    PRESENT(200, "이미 존재하는 값 입니다!", "It is already existed!"),
    CHECK_VALUE(200, "입력값을 확인 해 주세요!", "Please check the input value!"),

    CREATE(201, "리소스 생성이 완료 되었습니다!", "Resource creation has been completed!"),

    NO_CONTENT(204, "검색 결과가 없습니다.", "Not Found Data"),

    NotFoundUser(300, "사용자 정보를 찾을 수 없습니다.", "Not Found User"),
    TokenError(302, "요청한 토큰에 대한 세션정보가 없습니다. 토큰 재발급 필요.", "TokenError"),
    RequiredParam(400, "필수 파라미터 에러", "Required Parameter"),
    InvalidValueType(400, "파라미터 데이터 타입 오류", "Parameter valueType is not correct"),

    NeedSignIn(401, "로그인이 필요합니다.", "Need SignIn"),

    UnauthorisedAPICategory(403, "권한이 없는 API CategoryCategory 입니다.", "Unauthorised API Category"),
    UnauthorisedAPI(403, "권한이 없는 API 입니다.", "Unauthorised API"),
    NotAllowCSRF(403, "허용하지 않는 Referer 입니다.", "Not Allow Referer"),

    NotFoundResult(404, "결과를 찾을 수 없습니다.", "Not Found Result"),
    NotFoundAPIKey(404, "APIKey를 찾을 수 없습니다.", "Not found APIKey"),
    NotFoundAPICategory(404, "Api Category를 찾을 수 없습니다.", "Not Found Api Category"),
    NotFoundAPIConfig(404, "Api Config를 찾을 수 없습니다.", "Not Found Api Config"),
    NotFoundEvent(404, "Event를 찾을 수 없습니다.", "Not Found Event"),
    EmptyIdsParameters(404, "ids 파라미터값이 없음.", "ids Parameter is Empty"),

    NotAllowMethod(405, "허용하지 않는 Method 입니다.", "Not Allow Method"),

    NotSupportedAPIType(500, "지원하지 않는 API Type 입니다.", "Not Supported API Type"),
    NotSupportedAPICategoryType(500, "지원하지 않는 API Category Type 입니다.", "Not Supported API Category Type"),
    NotAllowProtocol(500, "허용하지 않는 Protocol 입니다.", "Not Allow Protocol"),
    NotFoundFile(500, "빈 파일을 등록할 수 없습니다.", "Not Found File"),
    ServerError(500, "서버에러", "ServerError");

    Integer code;
    String messageKo;
    String messageEn;

    ResponseCode(Integer code, String messageKo, String messageEn) {
        this.code = code;
        this.messageKo = messageKo;
        this.messageEn = messageEn;
    } // 생성자 끝

} // enum class 끝
