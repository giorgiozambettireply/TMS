package com.zambetti.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("api/forms/**").permitAll();
                    authorize.anyRequest().denyAll();
                });//.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); //.httpBasic(Customizer.withDefaults());
        return http.build();
    }


}