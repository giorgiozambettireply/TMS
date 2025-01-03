package com.zambetti;

import com.zambetti.service.CustomUserDetailsService;
import com.zambetti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    public String createJwtToken(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, password));

        //final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        var user = userService.findByUsername(username);

        return jwtUtil.generateToken(user.getUsername(), user.getId(), user.getRole().toString());
    }
}