package com.devcommunity.junyharang.model.vo.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 회원 가입 시 이용될 VO
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.24 최초 작성
 *    주니하랑, 1.0.1, 2022.03.27 Validation 위한 내용 추가
 * </pre>
 *
 * @author 주니하랑
 * @version 주니하랑, 1.0.1, 2022.03.27 Validation 위한 내용 추가
 * @See ""
 * @see <a href=""></a>
 */

@Getter @Setter @ToString
public class CustomUserDetails implements UserDetails {

    private int userId;                                                 // 회원 고유 번호(PK)

    @NotBlank                                                                               // 공백이 아닌 문자 하나 이상 포함 및 Null이 아닌지 확인
    @Size(min = 1, max = 30)                                                                // 문자열 길이가 1 ~ 30사이
    @Pattern(regexp = "^[0-9a-zA-Z]+$")                                                      // 영(대,소)문자, 숫자만 사용 가능 패턴 허용
    private String username;                                            // 회원 ID

    @NotBlank
    @Pattern(regexp = "^[A-Za-z\\d$@$!%*?&]{8,15}$")        // 숫자, 문자, 특수문자 포함 8 ~ 15자리 비밀번호 패턴 허용
    private String password;                                            // 회원 비밀번호

    @NotBlank
    @Size(min = 1, max = 30)
    @Pattern(regexp = "^[0-9a-zA-Zㄱ-ㅎㅏ-ㅣ-가-힣]+$")                                      // 한글과 숫자, 영(대,소)문자만 사용 가능 패턴 허용
    private String nickname;                                            // 회원 별명

    @NotBlank
    @Email                                                                                  // Email 형식에 맞게 입력 되는지 확인
    @Size(min = 1, max = 30)
    @Pattern(regexp = "^[0-9a-zA-Z]+@[0-9a-zA-Z]+\\.([a-z]+)*$")                            // Email 형식에 맞는 (xxx@xx.xx) 정규식 패턴만 허용
    private String userEmail;                                           // 회원 Eamil 주소

    @NotBlank
    @Size(min = 10, max = 11)
    @Pattern(regexp = "^01(?:0|1|[6-9])?(\\d{3}|\\d{4})?(\\d{4})$")                        // 핸드폰 번호 Pattern
    private String userPhone;                                           // 회원 핸드폰 번호
//  private String teamName;                                            // 소속팀 이름
//  private String userKind;                                            // 회원 구분
    private boolean enable;                                             // 회원 상태
    private String authority;                                           // 회원 권한
    private String refreshToken;                                               // Refresh Token 저장을 위한 Member 변수

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {        // 이용자 권한 목록 반환 Method

        Collection<GrantedAuthority> auth = new ArrayList<>();

        auth.add(new SimpleGrantedAuthority(authority));

        return auth;
    } // getAuthorities() 끝

    @Override
    public boolean isAccountNonExpired() {     // 계정 만료 여부 | true : 만료 안됨, false : 만료
        return true;
    } // isAccountNonExpired() 끝

    @Override
    public boolean isAccountNonLocked() {     // 계정 잠김 여부 | true : 안 잠김, false : 잠김
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {     // 비밀번호 만료 여끝 | true : 만료 안됨, false : 만료
        return true;
    } // isCredentialsNonExpired() 끝

    @Override
    public boolean isEnabled() {         // 계정 활성화 여부 | true : 활성화, false : 비 활성화
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
} // class 끝
