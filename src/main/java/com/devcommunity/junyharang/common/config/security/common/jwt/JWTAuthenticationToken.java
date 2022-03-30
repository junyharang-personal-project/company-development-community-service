package com.devcommunity.junyharang.common.config.security.common.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Slf4j
public class JWTAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private final String credentials;

    public JWTAuthenticationToken(String principal, String credentials) {

        super(null);

        setAuthenticated(false);

        this.principal = principal;
        this.credentials = credentials;

    } // JWTAuthenticationToken(String principal, String credentials) 끝

    public JWTAuthenticationToken(Object principal, String credentials, Collection<? extends GrantedAuthority> authorities) {

        super(authorities);

        setAuthenticated(true);

        this.principal = principal;
        this.credentials = credentials;

    } // JWTAuthenticationToken(Object principal, String credentials, Collection<? extends GrantedAuthority> authorities) 끝

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
} // class 끝
