package com.zambetti.seeder;

import com.zambetti.entity.User;
import com.zambetti.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner seedDatabase() {
        return args -> addAdminIfNotExists();
    }

    private void addAdminIfNotExists() {
        if (userRepository.findByUsername("admin").isPresent())
            return;

        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("adminPassword"));  // Password should be hashed
        adminUser.setRole(User.Role.ADMIN);

        userRepository.save(adminUser);
    }

}
