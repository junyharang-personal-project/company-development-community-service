package com.devcommunity.junyharang.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 문자열 관련 처리를 위한 공통 Class
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.09 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.09 최초 작성
 * @See "Si Yeon Han"
 * @see <a href=""></a>
 */

@Slf4j
public class CustomStringUtil {

	/**
	 * 생성자를 private으로 지정하여 new 키워드로의 instance 생성을 방지한다.
	 */
	private CustomStringUtil() {

		log.info("StringUtil의 StringUtil()가 호출 되었습니다!");

	} // StringUtil() 끝

	/**
	 * 첫번째 매개변수 값이 NULL인지 확인하고, NULL이면 빈 값을 반환하고, 아니라면 첫번째 인자 반환
	 *
	 */

	public static String getString(Object str) {

		log.info("CustomStringUtil의 getString(Object str)가 호출 되었습니다!");
		log.info("첫번째 매개변수 값이 NULL인지 확인하고, NULL이면 빈 값을 반환하고, 아니라면 첫번째 인자 반환하겠습니다!");

		return NVL(str, "");

	} // getString(Object str) 끝

	/**
	 * 첫번째 매개변수 값이 NULL인지 확인하고, NULL이면 빈 값을 반환하고, 아니라면 첫번째 인자 반환
	 *
	 */

	public static String getString(String str) {

		log.info("CustomStringUtil의 getString(String str)가 호출 되었습니다!");
		log.info("첫번째 매개변수 값이 NULL인지 확인하고, NULL이면 빈 값을 반환하고, 아니라면 첫번째 인자 반환하겠습니다!");

		return NVL(str, "");

	}

	/**
	 * @param str 문자열
	 * @param str 문자열이 null일 경우
	 * @param req 문자열로 대체
	 * @return String 문자열이 null일 경우 대체 문자열, 그 외에는 문자열 그대로
	 */

	private static String NVL(Object str, String req) {

		log.info("CustomStringUtil의 NVL(Object str, String req)가 호출 되었습니다!");
		log.info("해당 Method는 SQL의 NVL()과 동일한 작업을 수행 합니다!");

		log.info("첫번째 매개변수로 들어온 문자열이 null이면 두번째 매개 변수로 들어온 내용으로 치환하고, 아니면 첫번째 매개 변수로 들어온 문자열을 반환하겠습니다!");
		return str == null ? req : String.valueOf(str);
	} // NVL(String str, String req) 끝

	/**
	 * @param str 문자열
	 * @param str 문자열이 null일 경우
	 * @param req 문자열로 대체
	 * @return String 문자열이 null일 경우 대체 문자열, 그 외에는 문자열 그대로
	 */

	private static String NVL(String str, String req) {

		log.info("CustomStringUtil의 NVL(String str, String req)가 호출 되었습니다!");
		log.info("해당 Method는 SQL의 NVL()과 동일한 작업을 수행 합니다!");

		log.info("첫번째 매개변수로 들어온 문자열이 null이면 두번째 매개 변수로 들어온 내용으로 치환하고, 아니면 첫번째 매개 변수로 들어온 문자열을 반환하겠습니다!");
		return str == null ? req : String.valueOf(str);
	} // NVL(String str, String req) 끝


} // class 끝
