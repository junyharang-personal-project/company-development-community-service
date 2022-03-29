package com.devcommunity.junyharang.common.config.security.common.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Token 생성, 유효성 검사 등 JWT 관리 Class
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.29 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.29 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

@PropertySource("classpath:/application.yml") @Component    // Bean 생성
public class TokenProvider implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private final String secret;
    private final long tokenValidityInMilliSeconds;

    private Key key;

    // 생성자 Bean Injection
    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliSeconds) {

        logger.info("InitializingBean를 구현한 TokenProvider의 생성자가 호출 되었습니다! 생성자를 통해 Bean이 Injection 됩니다!");

        this.secret = secret;
        this.tokenValidityInMilliSeconds = tokenValidityInMilliSeconds * 1000;
    }   // 생성자 끝

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("InitializingBean를 구현한 TokenProvider의 afterPropertiesSet()가 호출 되었습니다!");
        logger.info("최초 [secret] 값은 base64로 Decoding 하겠습니다!");

        byte[] keyBytes = Decoders.BASE64.decode(secret);

        logger.info("Decoding한 값을 key 변수에 저장하겠습니다!");

        this.key = Keys.hmacShaKeyFor(keyBytes);

    } // afterPropertiesSet() 끝

    public String createToken(Authentication authentication) {

        // Authentication 인터페이스 : 인증 정보를 의미하는 인터페이스
        // getAuthorities() : 계정이 가지고 있는 권한 목록을 리턴
        // stream() : 배열 또는 컬렉션 인스턴스에 함수 여러개를 조합해서 원하는 결과 필터링.
        // collect() : 요소들을 필터링/매핑한 후 요소들을 수집하는 최종 처리 메소드
        //             필요한 요소만 컬렉션으로 담을 수 있고, 요소들을 그룹화 한 후 집계할 수 있음
        // Collectors.joining() : StringBuilder를 생성한 후 계속 추가해준 후 반환
        // GrantedAuthority : ID, Password 기반 인증에서 UserDetailsService를 통해 조회

        logger.info("InitializingBean를 구현한 TokenProvider의 createToken(Authentication authentication)가 호출 되었습니다!");
        logger.info("Authentication 객체의 권한 정보를 이용하여 객체를 생성하는 Method 입니다.");

        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                // Stream 내 요소에 각각 GrantedAuthority의 getAuthority 실행
                .collect(Collectors.joining(","));// StringBuilder 생성 뒤 , 를 추가하여 반환

        long now = (new Date()).getTime();
        Date validityTime = new Date(now + this.tokenValidityInMilliSeconds);

        logger.info("JWT 만료 시간 설정을 하겠습니다! 이용자가 Login을 요청한 시각 기준 application.yrml에 설정한 JWT 만료 시간을 더해 유효 시간이 설정 됩니다!");

        return Jwts.builder()
                .setSubject(authentication.getName())       // JWT 이름, 현재 Code에서는 UserID
                .claim(AUTHORITIES_KEY, authorities)        // key : auto value : 권한들
                .signWith(key, SignatureAlgorithm.HS256)    // Decoding한 값을 HS512 Hash Algorithm으로 암호화
                .setExpiration(validityTime)                // JWT 만료 시간 설정
                .compact();                                 // 압축하라고 명령
    }   // createToken(Authentication authentication) 끝

    public Authentication getAuthentication(String token) {

        logger.info("InitializingBean를 구현한 TokenProvider의 getAuthentication(String token)가 호출 되었습니다!");
        logger.info("Token 검증을 담당하고, String Type 매개변수 token이 사용 가능한 형태로 Parsing하기 위해 jwtsparse를 이용하는 Method 입니다.");

        Claims claims = Jwts.parserBuilder()                //  jwtParserBuilder 인스턴스 생성
                .setSigningKey(key)         //  암호화 된 Key 변수 설정
                .build()                    //  Thread에 안전하게 반환하기 위해 호출
                .parseClaimsJws(token)      // Token을 jws로 Parsing
                .getBody();// Token에 저장되었던 DATA들이 담긴 claims를 가져온다.

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        logger.info("이용자의 권한을 이용하여 회원 객체를 생성 하겠습니다!");

        User principal = new User(claims.getSubject(), "", authorities);

        logger.info("회원 객체, JWT, 권한들을 담은 Authentication 객체를 반환 하겠습니다!");

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);

    } // getAuthentication(String token) 끝

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
} // class 끝
