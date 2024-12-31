package com.zambetti.service;

import com.zambetti.Util.JwtUtil;
import com.zambetti.entity.User;
import com.zambetti.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User registerUser(User user) {
        var dbUser = userRepository.findByUsername(user.getUsername());
        if(dbUser.isEmpty())
            throw new RuntimeException("An admin must activate this user before you can register.");

        if(dbUser.get().isAccountActive())
            throw new RuntimeException("The account is already active.");

        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Hash the password
        return userRepository.save(user);
    }

    public String loginUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty() ||
                !userOpt.get().isAccountActive() ||
                !passwordEncoder.matches(password, userOpt.get().getPassword()))
            throw new RuntimeException("Invalid username or password");

        return jwtUtil.generateToken(username, userOpt.get().getRole().toString(), userOpt.get().getId());
    }
}
