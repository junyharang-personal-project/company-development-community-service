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
public class StringUtil {

	/**
	 * 생성자를 private으로 지정하여 new 키워드로의 instance 생성을 방지한다.
	 */
	private StringUtil() {

		log.info("StringUtil의 StringUtil()가 호출 되었습니다!");

	} // StringUtil() 끝

	/**
	 * 날짜 형식 변환 (20220309 > 2022.03.09)
	 * @param value 날짜 데이타
	 * @return String 반환 된 문자
	 * @see ""
	 */

	public static String setConvertDate(String value) {
		log.info("StringUtil의 setConvertDate(String value)가 호출 되었습니다! 매개변수로 들어온 날짜 형식(200220309 > 2022.03.09) 변환을 실시 하겠습니다!");
		return setConvertDate(value, ".");
	} // setConvertDate(String value) 끝

	/**
	 * 날짜 형식 변환
	 * @param value     날짜 데이타
	 * @param delimiter 날짜 변환 구분자
	 * @return String 반환 된 문자
	 * @see ""
	 */

	public static String setConvertDate(String value, String delimiter) {

		log.info("StringUtil의 setConvertDate(String value, String delimiter)가 호출 되었습니다!");

		if (value.length() < 8) {
			return value;
		}

		// 변환 날짜 데이타
		String strRetValue = "";
		// 날짜 데이타 문자배열로 변환
		char[] arrCDate = value.toCharArray();

		// 합칠 날짜 데이타 변수
		StringBuilder sb = new StringBuilder(strRetValue);

		for (int count = 0; count < 8; count++) {
			sb.append(arrCDate[count]);

			if (count == 3 || count == 5) {
				sb.append(delimiter);
			}
		}

		strRetValue = sb.toString();

		return strRetValue;
	}

	/**
	 * 날짜 형식 변환 (20220309 > 2022.03.09)
	 * @param value 날짜 데이타
	 * @return String 반환 된 문자
	 * @see ""
	 */

	public static String reConvertDate(String value) {

		log.info("StringUtil의 reConvertDate(String value)가 호출 되었습니다!");

		// 날짜 데이타
		String chgValue = value;
		chgValue = chgValue.replace(".", "");

		return chgValue;
	}

	/**
	 * 문자열 합치기
	 * @param strings   문자배열
	 * @param delimiter 문자구분자
	 * @return String 반환 된 문자
	 * @see ""
	 */

	public static String join(String[] strings, String delimiter) {

		log.info("StringUtil의 join(String[] strings, String delimiter)가 호출 되었습니다!");

		// 합칠 문자 데이타 변수
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < strings.length - 1; i++) {
			sb.append(strings[i]).append(delimiter);
		}

		sb.append(strings[strings.length - 1]);

		return sb.toString();
	}

	/**
	 * 문자배열에 해당 문자가 존재하는지 체크
	 * @param str   체크문자
	 * @param array 문자배열
	 * @see ""
	 */

	public static boolean contains(String str, String[] array) {

		log.info("StringUtil의 contains(String str, String[] array)가 호출 되었습니다!");

		if (str == null) {
			return false;
		}

		// 문자배열 안의 문자수만큼 비교
		for (int i = 0; i < array.length; i++) {
			if (str.equals(array[i])) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 문자 데이타 값 존재 여부 체크
	 * @param sData 체크문자
	 * @return boolean true/false
	 * @see ""
	 */

	public static boolean isBlank(String sData) {

		log.info("StringUtil의 isBlank(String sData)가 호출 되었습니다!");
		return (sData == null || sData.length() == 0);
	}

	/**
	 * 전송 데이타 Cross Site Script 방지
	 * @param strData 체크 할 문자
	 * @return String 반환 된 문자
	 * @see ""
	 */

	public static String removeXSS(String strData) {

		log.info("StringUtil의 removeXSS(String strData)가 호출 되었습니다!");

		// 체크 할 문자배열
		String[] arrSrcCode = { "<", ">", "\"", "\'", "%00", "%" };
		// 체크 할 Tag 문자배열
		String[] arrTgtCode = { "&lt;", "&gt;", "&#34;", "&#39;", "null;", "&#37;" };

		if (strData == null || "".equals(strData)) {
			return "";
		}

		for (int nLoop = 0; nLoop < arrSrcCode.length; nLoop++) {
			strData = strData.replaceAll(arrSrcCode[nLoop], arrTgtCode[nLoop]);
		}

		return strData;
	}

	/**
	 * 쿼리단 Cross Site Script 방지
	 * @param strData 체크 할 문자
	 * @return String 반환 된 문자
	 * @see ""
	 */

	public static String removeQueryXSS(String strData) {

		log.info("StringUtil의 removeQueryXSS(String strData)가 호출 되었습니다!");

		// 체크 할 문자배열
		String[] arrSrcCode = { "<", ">", "\"", "\'", "%00", "%", ";", "-" };
		// 체크 할 Tag 문자배열
		String[] arrTgtCode = { "&lt;", "&gt;", "&#34;", "&#39;", "null;", "&#37;", "&#59;", "&#45;" };

		if (strData == null || "".equals(strData)) {
			return strData;
		}

		for (int nLoop = 0; nLoop < arrSrcCode.length; nLoop++) {
			strData = strData.replaceAll(arrSrcCode[nLoop], arrTgtCode[nLoop]);
		}

		return strData;
	}

	/**
	 * 입력된 데이타 토크나이징 처리 후 토크나이징된 토큰들을 문자배열로 반환
	 * @param pmString    토크나이징되는 문자
	 * @param pmDelimeter 문자열를 분리하는 문자
	 * @return String 반환 된 문자배열
	 * @see ""
	 */

	public static String[] getTokens(String pmString, String pmDelimeter) {

		log.info("StringUtil의 getTokens(String pmString, String pmDelimeter)가 호출 되었습니다!");

		// 토크나이징되는 문자
		String pmsDelimeter = pmDelimeter;
		// 문자열를 분리하는 문자
		String[] lmsReturns = null;

		if (pmString != null) {
			if ("\\".equals(pmsDelimeter)) {
				pmsDelimeter = "/";
			}

			StringTokenizer lmoTokenizer = new StringTokenizer(pmString, pmsDelimeter);
			lmsReturns = new String[lmoTokenizer.countTokens()];

			for (int i = 0; lmoTokenizer.hasMoreTokens(); i++) {
				lmsReturns[i] = lmoTokenizer.nextToken();
			}
		}
		return lmsReturns;
	}

	/**
	 * 문자열을 정의한 Byte로 잘라 끝을 ...으로 표현
	 * @param str           변환 시킬 문자
	 * @param putByteLength 문자열 자를 Byte
	 * @param delimiter     문자 구분자
	 * @return String 반환 된 문자
	 * @see ""
	 */

	public static String getStringByteCut(String str, int putByteLength, String delimiter) {

		log.info("StringUtil의 getStringByteCut(String str, int putByteLength, String delimiter)가 호출 되었습니다!");

		// 표현문자 길이
		int strLength = 0;

		// 비교할 문자배열
		char[] tempChar = new char[str.length()];
		// 합칠 문자 데이타 변수
		StringBuilder stb = new StringBuilder();

		for (int i = 0; i < tempChar.length; i++) {
			tempChar[i] = str.charAt(i);
			if (tempChar[i] < 128) {
				strLength++;
			} else {
				strLength += 2;
			}

			stb.append(tempChar[i]);

			if (strLength > putByteLength) {
				stb.append(delimiter);
				break;
			}
		}

		return stb.toString();
	}

	/**
	 * 자릿수에 0 채워주기
	 * @param n      변환 시킬 숫자
	 * @param cipher 자릿수
	 * @return String 반환 된 숫자
	 * @see ""
	 */

	public static String addZero(int n, int cipher) {

		log.info("StringUtil의 addZero(int n, int cipher)가 호출 되었습니다!");

		// 숫자 길이
		String num = String.valueOf(n);
		// 합칠 문자 데이타 변수
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < cipher - num.length(); i++) {
			str.append("0");
		}

		return str.append(num).toString();
	}

	/**
	 * 자릿수에 0 채워주기
	 * 이메일 유효성 체크
	 *
	 * @param email 체크 할 이메일주소
	 * @return boolean true/false
	 * @see ""
	 */

	public static boolean isValidEmail(String email) {

		log.info("StringUtil의 isValidEmail(String email)가 호출 되었습니다!");

		// 유효성 체크 여부
		boolean err = false;
		// 유효성 패턴
		Pattern p = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"
				+ "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");
		// 유효성 패턴 체크 여부
		Matcher m = p.matcher(email);

		if (!m.matches()) {
			err = true;
		}

		return err;
	}

	/**
	 * 문자 null 체크 후 null인 경우 공백 문자로 변환
	 *
	 * @param oData null 체크 할 object
	 * @return String 반환 된 문자
	 * @see ""
	 */

	public static String nvl(Object oData) {

		log.info("StringUtil의 String nvl(Object oData)가 호출 되었습니다!");

		return nvl(oData, "");
	}

	/**
	 * 문자 null 체크 후 null인 경우 대체 숫자로 변환
	 *
	 * @param oData  null 체크 할 object
	 * @param sTrans 대체 숫자
	 * @return String 반환 된 문자
	*/

	public static String nvl(Object oData, int sTrans) {

		log.info("StringUtil의 nvl(oData, Integer.toString(sTrans)가 호출 되었습니다!");

		return nvl(oData, Integer.toString(sTrans));
	}

	/**
	 * 문자 null 체크 후 null인 경우 대체 문자로 변환
	 *
	 * @param oData  null 체크 할 object
	 * @param sTrans 대체 문자
	 * @return String 반환 된 문자
	 */

	public static String nvl(Object oData, String sTrans) {

		log.info("StringUtil의 nvl(Object oData, String sTrans)가 호출 되었습니다!");

		if (sTrans == null) {
			sTrans = "";
		}

		if (oData != null && !"".equals(oData) && !"null".equals(oData)) {
			return removeXSS(oData.toString().trim());
		}

		return removeXSS(sTrans);
	}

	/**
	 * 숫자의 왼쪽 자리를 '0'으로 추가
	 *
	 * @param convert 변환 시킬 숫자
	 * @param size 추가 시킬 수
	 * @return String 반환 된 문자
	 */

	public static String padLeftwithZero(int convert, int size) {

		log.info("StringUtil의 padLeftwithZero(int convert, int size)가 호출 되었습니다!");

		// 합칠 문자 데이타 변수
		StringBuilder sbRtn = new StringBuilder();
		// 숫자 Integer형으로 변환 변수
		Integer inTemp = Integer.valueOf(convert);
		// Integer 문자열 변환 변수
		String stTemp;

		stTemp = inTemp.toString();

		if (stTemp.length() < size) {
			for (int i = 0; i < size - stTemp.length(); i++) {
				sbRtn.append("0");
			}
		}

		sbRtn.append(stTemp);

		return sbRtn.toString();
	}

	/**
	 * 왼쪽에 0이 붙은 문자를 제거
	 *
	 * @param sbRtn 변환 시킬 문자
	 * @return String 반환 된 문자
	 */

	public static String removeLeftZero(String sbRtn) {

		log.info("StringUtil의 removeLeftZero(String sbRtn)가 호출 되었습니다!");

		return sbRtn.replaceFirst("^0+(?!$)", "");
	}

	/*****************************************************
	 * 문자 null 체크 후 null인 경우 대체 숫자로 변환
	 * 
	 * @param sData  String : 데이터 값
	 * @param sTrans String : null, "", "null"일 경우 변경할값
	 * @return String
	 *****************************************************/
	public static int nvlInt(Object objData, int nTrans) {
		return Integer.parseInt(nvl(objData, nTrans));
	}

	/*****************************************************
	 * 문자열이 해당 사이즈 보다 작으면 작은 만큼 오른쪽에 문자로 채운다.
	 * 
	 * @param convert   : 문자열
	 * @param size      : 문자 총 사이즈(문자 총 사이즈 - 문자열사이즈 만큼 채운다)
	 * @param padString : 채울 문자열(ex: 공백, 0 등등)
	 * @return String
	 */
	public static String padRightwithString(String convert, int size, String padString) {
		StringBuilder stReturn = new StringBuilder();

		if (nvl(convert).length() < size) {
			for (int i = 0; i < size - nvl(convert).length(); i++) {
				stReturn.append(padString);
			}
		}

		return nvl(convert) + stReturn.toString();
	}

	/*****************************************************
	 * 문자열이 해당 사이즈 보다 작으면 작은 만큼 오른쪽에 문자로 채운다.(Byte 단위로 계산)
	 * 
	 * @param convert   : 문자열
	 * @param size      : 문자 총 사이즈(문자 총 사이즈 - 문자열사이즈 만큼 채운다)
	 * @param padString : 채울 문자열(ex: 공백, 0 등등)
	 * @return String
	 *****************************************************/
	public static String padRightwithByteString(String convert, int size, String padString) {
		StringBuilder stReturn = new StringBuilder();

		if (nvl(convert).length() < size) {
			for (int i = 0; i < size - nvl(convert).getBytes().length; i++) {
				stReturn.append(padString);
			}
		}

		return nvl(convert) + stReturn.toString();
	}

	/*****************************************************
	 * 문자열이 해당 사이즈 보다 작으면 작은 만큼 왼쪽에 문자로 채운다.
	 * 
	 * @param convert   : 문자열
	 * @param size      : 문자 총 사이즈(문자 총 사이즈 - 문자열사이즈 만큼 채운다)
	 * @param padString : 채울 문자열(ex: 공백, 0 등등)
	 * @return String
	 *****************************************************/
	public static String padLeftwithString(String convert, int size, String padString) {
		StringBuilder stReturn = new StringBuilder();

		if (nvl(convert).length() < size) {
			for (int i = 0; i < size - nvl(convert).length(); i++) {
				stReturn.append(padString);
			}
		}

		return stReturn.toString() + nvl(convert);
	}

	/*****************************************************
	 * 문자열이 해당 사이즈 보다 작으면 작은 만큼 왼쪽에 문자로 채운다.(Byte 단위로 계산)
	 * 
	 * @param convert   : 문자열
	 * @param size      : 문자 총 사이즈(문자 총 사이즈 - 문자열사이즈 만큼 채운다)
	 * @param padString : 채울 문자열(ex: 공백, 0 등등)
	 * @return String
	 *****************************************************/
	public static String padLeftwithByteString(String convert, int size, String padString) {
		StringBuilder stReturn = new StringBuilder();

		if (nvl(convert).length() < size) {
			for (int i = 0; i < size - nvl(convert).getBytes().length; i++) {
				stReturn.append(padString);
			}
		}

		return stReturn.toString() + nvl(convert);
	}

	/*****************************************************
	 * 파일 Path 방어 코딩(..경로 치환 작업)
	 * 
	 * @param strPath : 경로
	 * @return String
	 *****************************************************/
	public static String paramPathDefense(String strPath) {
		String strDefensePath = strPath;
		strDefensePath = strDefensePath.replaceAll("\\.\\./", "");
		strDefensePath = strDefensePath.replaceAll("\\.\\.\\\\", "");

		return strDefensePath;
	}

	/*****************************************************
	 * US7ASCII로 인코딩된 문자열을 한글로 변환하여 반환
	 * 
	 * @param value US7ASCII로 인코딩된 문자열
	 * @return String 한글로 변환된 문자열
	 *****************************************************/
	public static String encodeKoreanFromUs7ascii(String value) {
		if (value == null) {
			return "";
		}

		String str = "";

		try {
			str = new String(value.getBytes(StandardCharsets.ISO_8859_1), "KSC5601");
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}

		return str;
	}

	/*****************************************************
	 * 내용에 {0-9}의 문자를 배열값으로 치환
	 * 
	 * @param orgnString 내용
	 * @param args       치환배열
	 * @return String 치환내용
	 *****************************************************/
	public static String getArgumentsString(String orgnString, String[] args) {
		args = Optional.ofNullable(args).orElse(new String[] {});

		Pattern pattern = Pattern.compile("\\{[0-9]*\\}");
		Matcher matcher = pattern.matcher(orgnString);

		StringBuilder stringBuilder = new StringBuilder();
		int startIndex = 0;

		while (matcher.find()) {
			stringBuilder.append(orgnString, startIndex, matcher.start());

			try {
				stringBuilder
						.append(args[Integer.parseInt(matcher.group().substring(1, matcher.group().length() - 1))]);
			} catch (Exception e) {
				stringBuilder.append(matcher.group());
			}

			startIndex = matcher.end();
		}

		if (stringBuilder.length() > 0) {
			stringBuilder.append(orgnString.substring(startIndex));
		} else {
			stringBuilder.append(orgnString);
		}

		return stringBuilder.toString();
	}
	/////////////////////////////////////////////////////////////////////////////////////

	  /**
	   * 첫번째 파라미터 값이 NULL인지 체크 한 후 NULL 이라면 두번째 인자로 반환하고 아니라면 첫번째 인자를 반환한다.
	   *
	   * <p>
	   * 
	   * @param String str
	   * @param String defaultValue
	   * @return String
	   */
	  public static String getString(String str, String defaultValue) {
	    return (str == null || str.equals("")) ? defaultValue : str.trim();
	    // return NVL(str, defaultValue);
	  }
	  /**
	   * 첫번째 파라미터 값이 NULL인지 체크 한 후 NULL 이라면 두번째 인자로 반환하고 아니라면 첫번째 인자를 반환한다.
	   *
	   * <p>
	   * 
	   * @param Object str
	   * @param String defaultValue
	   * @return
	   */
	  public static String getString(Object str, String defaultValue) {
	    return (str == null || str.equals("")) ? defaultValue : ((String) str).trim();
	    // return NVL(str, defaultValue);
	  }

	  /**
	   * 첫번째 파라미터 값이 NULL인지 체크 한 후 NULL 이라면 빈 값을 반환하고 아니라면 첫번째 인자를 반환한다.
	   *
	   * <p>
	   * 
	   * @param Object str
	   * @return String
	   */
	  public static String getString(Object str) {
	    return NVL(str, "");
	  }
	  
	  /**
	   * 첫번째 파라미터 값이 NULL인지 체크 한 후 NULL 이라면 빈 값을 반환하고 아니라면 첫번째 인자를 반환한다.
	   *
	   * <p>
	   * 
	   * @param String str
	   * @return String
	   */
	  public static String getString(String str) {
	    return NVL(str, "");
	  }

	  /**
	   * 문자열의 교체 (legacy)
	   *
	   * @param obj    문자열
	   * @param oldsub 변경시킬 문자열
	   * @param newsub 변경할 문자열
	   * @return String 변경된 문자열
	   * @see String#replaceAll(String, String)
	   */
	  public static String replace(Object obj, String oldsub, String newsub) {
	    if ((obj == null) || (oldsub == null) || (newsub == null)) {
	      return null;
	    }
	    return replace(getString(obj), oldsub, newsub);
	  }

	  /**
	   * 문자열의 교체 (legacy)
	   *
	   * @param str    문자열
	   * @param oldsub 변경시킬 문자열
	   * @param newsub 변경할 문자열
	   * @return String 변경된 문자열
	   * @see String#replaceAll(String, String)
	   */
	  public static String replace(String str, String oldsub, String newsub) {
	    if ((str == null) || (oldsub == null) || (newsub == null)) {
	      return null;
	    }
	    StringBuffer sb = new StringBuffer();
	    int length = oldsub.length();
	    int x = 0;
	    int y = str.indexOf(oldsub);

	    while (x <= y) {
	      sb.append(str.substring(x, y));
	      sb.append(newsub);
	      x = y + length;
	      y = str.indexOf(oldsub, x);
	    }

	    sb.append(str.substring(x));

	    return sb.toString();
	  }

	  /**
	   * @param str 문자열
	   * @return String 문자열이 null일 경우 "", 그외에는 문자열 그대로
	   */
	  public static String NVL(String str) {
	    return NVL(str, "");
	  }

	  /**
	   * @param str 문자열
	   * @param rep 문자열이 null일 경우 대체 문자열
	   * @return String 문자열이 null일 경우 대체 문자열, 그외에는 문자열 그대로
	   */
	  public static String NVL(String str, String rep) {
	    return str == null ? rep : str;
	  }
	  
	  /**
	   * @param str 문자열
	   * @param rep 문자열이 null일 경우 대체 문자열
	   * @return String 문자열이 null일 경우 대체 문자열, 그외에는 문자열 그대로
	   */
	  public static String NVL(Object str, String rep) {
	    //return str == null ? rep : (String) str;
	    return str == null ? rep : String.valueOf(str);
	  }

	  /**
	   * byte[]를 String으로 변환
	   *
	   * <p>
	   *
	   * @param byte[] data
	   * @return String
	   */
	  public static String byteToHex(byte[] data) {
	    StringBuffer buf = new StringBuffer();
	    for (int i = 0; i < data.length; i++) {
	      // buf.append(byteToHex(data[i]).toUpperCase());
	      buf.append(byteToHex(data[i]));
	    }
	    return buf.toString();
	  }

	  /**
	   * byte를 String으로 변환
	   *
	   * <p>
	   *
	   * @param byte data
	   * @return String
	   */
	  public static String byteToHex(byte data) {
	    StringBuffer buf = new StringBuffer();
	    buf.append(hexToChar((data >>> 4) & 0x0F));
	    buf.append(hexToChar(data & 0x0F));
	    return buf.toString();
	  }

	  /**
	   * 헥사값을 char로 변환
	   *
	   * <p>
	   *
	   * @param int i
	   * @return char
	   */
	  public static char hexToChar(int i) {
	    if ((i >= 0) && (i <= 9)) {
	      return (char) ('0' + i);
	    } else {
	      return (char) ('a' + (i - 10));
	    }
	  }

	  /**
	   * <pre>
	   *
	   * desc : String을 int로 반환 기본값은 0
	   *
	   * </pre>
	   * 
	   * @param str
	   * @return int
	   */
	  public static int getInt(String str) {
	    return getInt(str, 0);
	  }
	  
	  /**
	   * <pre>
	   *
	   * desc : String을 int로 반환 기본값은 0
	   *
	   * </pre>
	   * 
	   * @param str
	   * @return int
	   */
	  public static int getInt(Object str) {
	    return getInt(getString(str), 0);
	  }

	  /**
	   * <pre>
	   *
	   * desc : String을 int로 반환, 에러 발생시 두번째 값 반환
	   *
	   * </pre>
	   * 
	   * @param str         value
	   * @param default_int 기본값
	   * @return int
	   */
	  public static int getInt(String str, int default_int) {
	    try {
	      return Integer.parseInt(str);
	    }catch(NumberFormatException ne ) {
	    	return default_int;
	    }catch (Exception e) {
	      return default_int;
	    }
	  }

	  /**
	   * <pre>
	   *
	   * desc : String을 boolean으로 반환
	   *
	   * </pre>
	   * 
	   * @param str          value
	   * @param defaultValue 기본값
	   * @return boolean
	   */
	  public static boolean getBoolean(String str, boolean defaultValue)throws Exception {
	    try {
	      return (str == null || str.equals("")) ? defaultValue : (Boolean.valueOf(str)).booleanValue();
	    }catch (NullPointerException npe) {
	    	throw npe;
	    }catch (Exception e) {
	      return defaultValue;
	    }
	  }

	  /**
	   * <pre>
	   *
	   * desc : String을 boolean으로 반환
	   *
	   * </pre>
	   * 
	   * @param str value
	   * @return boolean
	   */
	  public static boolean getBoolean(String str)throws Exception {
	    boolean defaultValue = false;
	    return getBoolean(str, defaultValue);
	  }

	  /**
	   * <pre>
	   *
	   * desc : String을 long으로 반환
	   *
	   * </pre>
	   * 
	   * @param str
	   * @return
	   */
	  public static long getLong(String str) {
	    return getLong(str, 0l);
	  }

	  /**
	   * <pre>
	   *
	   * desc : String을 long으로 반환
	   *
	   * </pre>
	   * 
	   * @param str
	   * @param default_long
	   * @return
	   */
	  public static long getLong(String str, long default_long) {
	    try {
	      return Long.parseLong(str);
	    }catch(NumberFormatException ne) {
	    	return default_long;
	    }catch (Exception e) {
	      return default_long;
	    }
	  }

	  /**
	   * <pre>
	   *
	   * desc : String을 double로 반환
	   *
	   * </pre>
	   * 
	   * @param str
	   * @return
	   */
	  public static double getDouble(String str) {
	    return getDouble(str, 0d);
	  }

	  /**
	   * <pre>
	   *
	   * desc : String을 double로 반환
	   *
	   * </pre>
	   * 
	   * @param str
	   * @param default_double
	   * @return
	   */
	  public static double getDouble(String str, double default_double) {
	    try {
	      return Double.parseDouble(str);
	    } catch(NumberFormatException ne) {
	    	return default_double;
	    }catch (Exception e) {
	      return default_double;
	    }
	  }

	  
	  /**
	   * <pre>
	   *
	   * desc : Objet을 double로 반환
	   *
	   * </pre>
	   * 
	   * @param obj
	   * @return
	   */
	  public static double getDouble(Object obj) {
	    return getDouble(obj, 0d);
	  }
	  /**
	   * <pre>
	   *
	   * desc : Object을 double로 반환
	   *
	   * </pre>
	   * 
	   * @param obj
	   * @param default_double
	   * @return
	   */
	  public static double getDouble(Object obj, double default_double) {
	    try {
	      return Double.parseDouble(obj.toString());
	    }catch(NumberFormatException ne) {
	    	return default_double;
	    }catch (Exception e) {
	      return default_double;
	    }
	  }
	  
	  /**
	   * 의미있는 String인지 여부 판단 null과 empty일때 false
	   *
	   * @param s
	   * @return
	   */
	  public static boolean isSemantic(String s) {
	    if (s == null) {
	      return false;
	    }
	    for (int i = 0; i < s.length(); i++) {
	      if (!Character.isWhitespace(s.charAt(i))) {
	        return true;
	      }
	    }
	    return false;
	  }
	  
	  /**
	   * 문자열 길이를 반환
	   * 
	   * @param s : 문자열
	   * @param charset : 캐릭터셋
	   * @return
	   * @throws UnsupportedEncodingException 
	   */
	  public static int getStringLength(String s, String charset) throws UnsupportedEncodingException {
	    if(s.isEmpty()) return 0;
	    return s.getBytes(charset).length;
	  }
	  /**
	   * 문자열 길이를 반환 (default:utf-8)
	   * 
	   * @param s
	   * @return
	   * @throws UnsupportedEncodingException 
	   */
	  public static int getStringLength(String s) throws UnsupportedEncodingException {
	    if(s.isEmpty()) return 0;
	    else return s.getBytes("utf-8").length;
	  }

	  /**
	   * null 또는 "" 일 경우 true
	   * 
	   * @param string
	   * @return true
	   */
	  public static boolean isEmpty(String string) {
	    return !isSemantic(string);
	  }
	  public static boolean isEmpty(Object obj) {
	    return !isSemantic(getString(obj));
	  }
	  
	  /**
	   * null 또는 "" 가 아닌경우 경우 true
	   * @param value
	   * @return true
	   */
	  public static boolean isNotEmpty(Object value) {
	    return !isEmpty(value);
	  }  

	  public static String encodeXSS(String value) {
	    // You'll need to remove the spaces from the html entities below
	    value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	    value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
	    value = value.replaceAll("'", "&#39;");
	    value = value.replaceAll("\"", "&quot;");
	    value = value.replaceAll("eval\\((.*)\\)", "");
	    value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
	    //value = value.replaceAll("script", "");
	    return value;
	  }

	  public static String decodeXSS(String value) {
	    if(value == null || value.equals("")) return value;
	    // You'll need to remove the spaces from the html entities below
	    value = value.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
	    value = value.replaceAll("&#40;", "\\(").replaceAll("&#41;", "\\)");
	    value = value.replaceAll("&#39;", "'");
	    value = value.replaceAll("&quot;", "\"");
	    return value;
	  }
	  
	  /**
	   * 문자열 길이만큼 반환
	   * 
	   * @param o
	   * @param startIndex
	   * @return
	   * @throws UnsupportedEncodingException
	   */
	  public static String getSubstring(Object o, int startIndex) throws UnsupportedEncodingException {
	    return getString(o).substring(startIndex);
	  }
	  
	  /**
	   * 문자열 길이만큼 반환
	   * 
	   * @param o
	   * @param startIndex
	   * @return
	   * @throws UnsupportedEncodingException
	   */
	  public static String getSubstring(String s, int startIndex) throws UnsupportedEncodingException {
	    return s.substring(startIndex);
	  }
	  
	  /**
	   * 문자열 길이만큼 반환
	   * 
	   * @param o
	   * @param startIndex
	   * @param endIndex
	   * @return
	   * @throws UnsupportedEncodingException
	   */
	  public static String getSubstring(Object o, int startIndex, int endIndex) throws UnsupportedEncodingException {
	    return getSubstring(getString(o), startIndex, endIndex);
	  }
	  
	  /**
	   * 문자열 길이만큼 반환
	   * 
	   * @param s
	   * @param startIndex
	   * @param endIndex
	   * @return
	   * @throws UnsupportedEncodingException
	   */
	  public static String getSubstring(String s, int startIndex, int endIndex) throws UnsupportedEncodingException {
	    if(s.isEmpty()) return "";
	    else if(s.length() <= startIndex) {
	      return "";
	    }
	    else if(s.length() > startIndex && s.length() >= endIndex) {
	      return s.substring(startIndex, endIndex);
	    }
	    else if(s.length() > startIndex && s.length() < endIndex) {
	      return s.substring(startIndex);
	    }
	    else {
	      return "";
	    }
	  }

	  /**
	   * 반각문자를 전각문자로 변환
	   * 
	   * @param src
	   * @return
	   */
	  public static String toFullChar(String src) {
	    if (src == null)
	      return null;
	    StringBuffer strBuf = new StringBuffer();
	    char c = 0;
	    for (int i = 0; i < src.length(); i++) {
	      c = src.charAt(i);
	      // 영문 알파벳 이거나 특수 문자
	      if (c >= 0x21 && c <= 0x7e)
	        c += 0xfee0;
	      // 공백
	      else if (c == 0x20)
	        c = 0x3000;
	      strBuf.append(c);
	    }
	    return strBuf.toString();
	  }
	  
	  /**
	   * 반각문자를 전각문자로 변환 (영문 제외)
	   * 
	   * @param src
	   * @return
	   */
	  public static String toFullCharExcludeEn(String src) {
	    if (src == null)
	      return null;
	    StringBuffer strBuf = new StringBuffer();
	    char c = 0;
	    for (int i = 0; i < src.length(); i++) {
	      c = src.charAt(i);
	      // 영문 제외
	      //if (c >= 0x21 && c <= 0x7e) {
	      if ((c >= 0x21 && c <= 0x40) || (c >= 0x5B && c <= 0x60) || (c >= 0x7B && c <= 0x7E)) {
	        c += 0xfee0;
	      }
	      strBuf.append(c);
	    }
	    return strBuf.toString();
	  }

	  /**
	   * 전각문자를 반각문자로 변환
	   * 
	   * @param src
	   * @return
	   */
	  public static String toHalfChar(String src) {
	    StringBuffer strBuf = new StringBuffer();
	    char c = 0;
	    for (int i = 0; i < src.length(); i++) {
	      c = src.charAt(i);
	      // 영문이거나 특수 문자 일경우.
	      if (c >= '！' && c <= '～')
	        c -= 0xfee0;
	      // 공백
	      else if (c == '　')
	        c = 0x20;
	      strBuf.append(c);
	    }
	    return strBuf.toString();
	  }
	  
	  /**
	   * value 값을 target 과 비교하여 같을경우 true
	   * @param value
	   * @param target
	   * @return boolean
	   */
	  public static boolean equals(Object value, String target) {
		    return getString(value, "").equals(target);
	  }  

	  /**
	   * 중괄호 치환
	   * @return String
	   */
	  public static String replaceDoubleCurlyBrace(Object o, Object... arr_replace) {
	    String str = getString(o);
	    for(int i=0; i<arr_replace.length; i++) {
	      str = str.replace("{{"+i+"}}",String.valueOf(arr_replace[i]));
	    }
	    return str;
	  }
	  /**
	   * 중괄호 치환
	   * @return String
	   */
	  public static String replaceDoubleCurlyBrace(String str, String... arr_replace) {
	    for(int i=0; i<arr_replace.length; i++) {
	      str = str.replace("{{"+i+"}}",arr_replace[i]);
	    }
	    return str;
	  }
	
}
