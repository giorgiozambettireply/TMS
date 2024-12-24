package com.zambetti.controller;


import com.zambetti.entity.User;
import com.zambetti.service.AuthService;
import com.zambetti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        User savedUser = authService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam(name = "username")  String username, @RequestParam(name = "password")  String password) {
        String token = authService.loginUser(username, password);
        return ResponseEntity.ok(token);
    }
    /* Debug methods: TODO: remove
    @GetMapping("/ping")
    public ResponseEntity<String> ping(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok("pong " + name);
    }

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