package com.zambetti;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

@Getter
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final Long userId;
    private final String role;

    public JwtAuthenticationToken(String username, Long userId, String role) {
        super(username, null, Collections.singletonList(new SimpleGrantedAuthority(role)));
        this.userId = userId;
        this.role = role;
    }

}