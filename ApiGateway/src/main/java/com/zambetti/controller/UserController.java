package com.zambetti.controller;

import com.zambetti.entity.User;
import com.zambetti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name="id") Long id) {
        var user = userService.findById(id);
        return getUserOrNotFound(user);
    }
/*
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable(name="username") String username) {
        var user = userService.getUserByUsername(username);
        return getUserOrNotFound(user);
    }
*/
    private ResponseEntity<User> getUserOrNotFound(Optional<User> user)
    {
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}