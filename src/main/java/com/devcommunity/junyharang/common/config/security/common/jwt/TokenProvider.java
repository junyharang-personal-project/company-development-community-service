package com.devcommunity.junyharang.common.config.security.common.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Token 생성, 유효성 검사 등 JWT 관리 Class
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.29 최초 작성
 *    주니하랑, 1.1.0, 2022.03.31 JWT(Access Token, Refresh Token) 기능 재 구성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.1.0, 2022.03.31 JWT(Access Token, Refresh Token) 기능 재 구성
 * @See ""
 * @see <a href=""></a>
 */

@PropertySource("classpath:/application.yml") @Component    // Bean 생성
public class TokenProvider {

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private static Key key;

    // Refresh Token 유효 시간 2주(ms단위)
    public static Long REFRESH_TOKEN_VALID_TIME = 14 * 1440 * 60 * 1000L;

    // Access Token 유효 시간 15분
    public static Long ACCESS_TOKEN_VALID_TIME = 15 * 60 * 1000L;

    public static String ACCESS_TOKEN_NAME = "ACCESS TOKEN";
    public static String REFRESH_TOKEN_NAME = "REFRESH TOKEN";

    // 생성자 Bean Injection
    public TokenProvider(@Value("${jwt.secret}") String secret){

        logger.info("InitializingBean를 구현한 TokenProvider의 생성자가 호출 되었습니다! 생성자를 통해 Bean이 Injection 됩니다!");

        key = Keys.hmacShaKeyFor(secret.getBytes());
    }   // 생성자 끝

    public static String createAccessToken(int userId, String authority) {  // 이용자가 정상적으로 로그인을 시도하면 JWT(Access Token) 생성하는 Method

        // Authentication 인터페이스 : 인증 정보를 의미하는 인터페이스
        // getAuthorities() : 계정이 가지고 있는 권한 목록을 리턴
        // stream() : 배열 또는 컬렉션 인스턴스에 함수 여러개를 조합해서 원하는 결과 필터링.
        // collect() : 요소들을 필터링/매핑한 후 요소들을 수집하는 최종 처리 메소드
        //             필요한 요소만 컬렉션으로 담을 수 있고, 요소들을 그룹화 한 후 집계할 수 있음
        // Collectors.joining() : StringBuilder를 생성한 후 계속 추가해준 후 반환
        // GrantedAuthority : ID, Password 기반 인증에서 UserDetailsService를 통해 조회

        Date now = new Date();

        return Jwts.builder()
                // 회원 정보 저장
                .claim("user_pk", userId)
                .claim("user_role", authority)
                .claim("token_name", ACCESS_TOKEN_NAME)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();


    }   // createAccessToken(int userId, String authority) 끝

    public static String createRefreshToken(int userId, String authority) {     // // 이용자가 정상적으로 로그인을 시도하면 JWT(Refresh Token) 생성하는 Method

        Date now = new Date();

        return Jwts.builder()
                .claim("user_pk", userId)
                .claim("user_role", authority)
                .claim("token_name", REFRESH_TOKEN_NAME)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    } // createRefreshToken(int userId, String authority) 끝

    public boolean validateToken(String token) {

        logger.info("InitializingBean를 구현한 TokenProvider의 validateToken(String token)이 호출 되었습니다!");
        logger.info("이 Method는 JWT 유효성 검증을 위한 Method 입니다!");

        try {

            logger.info("JWT Parsing 작업을 시작 하겠습니다!");

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            logger.info("JWT Parsing 작업이 완료되어 'true'를 반환 하겠습니다!");

            return true;

        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {

            e.getStackTrace();
            logger.warn("잘못된 JWT 서명으로 인해 catch (io.jsonwebtoken.security.SecurityException e)가 동작 하였습니다!");
            logger.warn(e.getMessage());

        } catch (ExpiredJwtException e) {

            e.getStackTrace();
            logger.warn("만료된 JWT로 인해 catch (ExpiredJwtException e)가 동작 하였습니다!");
            logger.warn(e.getMessage());

        } catch (UnsupportedJwtException e) {

            e.getStackTrace();
            logger.warn("지원되지 않은 JWT로 인해 catch (UnsupportedJwtException e)가 동작 하였습니다!");
            logger.warn(e.getMessage());

        } catch (IllegalArgumentException e) {

            e.getStackTrace();
            logger.warn("잘못 된 JWT로 인해 catch (IllegalArgumentException e)가 동작 하였습니다!");
            logger.warn(e.getMessage());

        } // try-catch 끝

        logger.info("JWT 유효성 검사 실패로 'false' 반환 하겠습니다!");

        return false;

    } // validateToken(String token) 끝

    public static Claims getClaims(String token) {      // JWT에서 Claim 추출 Method

        try {
            return Jwts.parserBuilder()                //  jwtParserBuilder 인스턴스 생성
                    .setSigningKey(key)                //  암호화 된 Key 변수 설정
                    .build()                           //  Thread에 안전하게 반환하기 위해 호출
                    .parseClaimsJws(token)             // Token을 jws로 Parsing
                    .getBody();                        // Token에 저장되었던 DATA들이 담긴 claims를 가져온다.
        } catch (ExpiredJwtException e) {

            e.getStackTrace();

            return e.getClaims();
        } // try-catch 끝
    } // getClaims(String token) 끝
} // class 끝
