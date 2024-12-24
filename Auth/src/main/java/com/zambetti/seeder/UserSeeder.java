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
        return args -> {
            // Check if an admin user already exists
            if (userRepository.findByUsername("admin").isEmpty()) {
                // Create a new admin user
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("adminPassword"));  // Password should be hashed
                //adminUser.setRole("ROLE_ADMIN");
                //adminUser.setActive(true);
                //adminUser.setCreatedAt(LocalDateTime.now());

                // Save the admin user
                userRepository.save(adminUser);
                System.out.println("Admin user created.");
            } else {
                System.out.println("Admin user already exists.");
            }
        };
    }

}
