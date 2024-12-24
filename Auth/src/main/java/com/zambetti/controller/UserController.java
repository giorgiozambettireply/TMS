package com.zambetti.controller;


import com.zambetti.entity.User;
import com.zambetti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam(name = "username")  String username, @RequestParam(name = "password")  String password) {
        String token = userService.loginUser(username, password);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok("pong " + name);
    }
    // Other CRUD methods...
}