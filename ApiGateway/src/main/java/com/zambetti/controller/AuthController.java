package com.zambetti.controller;


import com.zambetti.JwtAuthenticationToken;
import com.zambetti.JwtService;
import com.zambetti.entity.User;
import com.zambetti.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        User savedUser = authService.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam(name = "username")  String username, @RequestParam(name = "password")  String password) {
        final String jwt = jwtService.createJwtToken(username, password);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok("pong " + name);
    }


    @GetMapping("/info")
    public Mono<Map<String, Object>> getUserInfo(@AuthenticationPrincipal JwtAuthenticationToken authentication) {
        if (authentication == null) {
            return Mono.just(Collections.emptyMap());
        }
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", authentication.getName());
        userInfo.put("id", authentication.getUserId());
        userInfo.put("role", authentication.getRole());
        return Mono.just(userInfo);
    }

 /* TODO: remove debug methods
    @GetMapping("/role")
    public ResponseEntity<String> getUserRole(Authentication authentication) {
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            if (!authorities.isEmpty()) {
                String roles = authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(", "));
                return ResponseEntity.ok("Your roles: " + roles);
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No roles assigned to this user");
    }
 */
}