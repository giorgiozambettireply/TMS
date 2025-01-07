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
            addAdminIfNotExists();
            addManagerIfNotExists("manager1");
            addManagerIfNotExists("manager2");
            addEmployeeIfNotExists("employee1");
            addEmployeeIfNotExists("employee2");
            addEmployeeIfNotExists("employee3");
        };
    }

    private void addAdminIfNotExists() {
        addUserIfNotExists("admin","admin",User.Role.ADMIN);
    }

    private void addManagerIfNotExists(String name) {
        addUserIfNotExists(name,name,User.Role.MANAGER);
    }

    private void addEmployeeIfNotExists(String name) {
        addUserIfNotExists(name,name,User.Role.EMPLOYEE);
    }

    private void addUserIfNotExists(String name, String password, User.Role role) {
        if (userRepository.findByUsername(name).isPresent())
            return;

        User user = new User();
        user.setUsername(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        userRepository.save(user);
    }

}
