package com.devcommunity.junyharang.model.vo.member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 회원 가입 시 이용될 VO
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.24 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.24 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

public class CustomUserDetails implements UserDetails {

    private int userId;                 // 회원 고유 번호(PK)
    private String username;            // 회원 ID
    private String password;            // 회원 비밀번호
    private String nickname;            // 회원 별명
    private String userEmail;           // 회원 Eamil 주소
    private String userPhone;           // 회원 핸드폰 번호
    private String teamName;            // 소속팀 이름
    private String userKind;            // 회원 구분
    private boolean enable;             // 회원 상태
    private String authority;           // 회원 권한


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {        // 이용자 권한 목록 반환 Method

        ArrayList<GrantedAuthority> auth = new ArrayList<>();

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getUserKind() {
        return userKind;
    }

    public void setUserKind(String userKind) {
        this.userKind = userKind;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

} // class 끝
